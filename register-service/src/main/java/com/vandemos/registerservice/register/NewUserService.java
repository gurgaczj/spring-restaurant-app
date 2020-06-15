package com.vandemos.registerservice.register;

import com.vandemos.registerservice.dao.Registration;
import com.vandemos.registerservice.dao.Role;
import com.vandemos.registerservice.dao.User;
import com.vandemos.registerservice.dao.UserInfo;
import com.vandemos.registerservice.exception.ConfirmationException;
import com.vandemos.registerservice.exception.RegisterException;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.model.RoleEnum;
import com.vandemos.registerservice.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NewUserService {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public NewUserService(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveNewUser(RegisterModel registerModel, String hash) {
        if (usernameExist(registerModel.getUsername())) {
            throw new RegisterException("Nazwa użytkownika jest już zajęta");
        }

        if (emailExist(registerModel.getEmail())) {
            throw new RegisterException("Adres email jest już zajęty");
        }

        User user = new User();
        user.setUsername(registerModel.getUsername());
        user.setEmail(registerModel.getEmail());
        user.setPassword(passwordEncoder.encode(registerModel.getPassword()));
        user.setActivated(false);
        user.setEnabled(false);

        UserInfo userInfo = registerModel.getUser().toDao();
        userInfo.setUser(null);
        userInfo.getAddress().setUser(null);

        Registration registration = new Registration();
        registration.setRegistrationDate(LocalDateTime.now());
        registration.setHash(hash);
        registration.setActivationDate(null);

        Role role = roleService.findByRoleEnum(RoleEnum.USER);
        user.setRole(role);
        user.setUserInfo(userInfo);
        user.setRegistration(registration);

        User newUser = userService.save(user);

        return newUser != null;
    }

    public boolean usernameExist(String username) {
        try {
            userService.findByUsername(username);
        } catch (RuntimeException e){
            return false;
        }
        return true;
    }

    public boolean emailExist(String email) {
        try {
            userService.findByEmail(email);
        } catch (RuntimeException e){
            return false;
        }
        return true;
    }

    public void confirmAccount(String username, String hash) {
        User user = userService.findByUsername(username);

        if(user.isActivated()){
            throw new ConfirmationException("Konto zostało już aktywowane.");
        }

        LocalDateTime registrationDate = user.getRegistration().getRegistrationDate();
        LocalDateTime threeDaysLater = LocalDateTime.of(registrationDate.getYear(), registrationDate.getMonth(),
                registrationDate.getDayOfMonth() + 3, registrationDate.getHour(), registrationDate.getMinute());

        if(LocalDateTime.now().isAfter(threeDaysLater)){
            throw new ConfirmationException("Minął czas na aktywacje.");
        }

        if(user.getRegistration().getHash().equals(hash)){
            user.setEnabled(true);
            user.setActivated(true);
            user.getRegistration().setActivationDate(LocalDateTime.now());
        } else {
            throw new ConfirmationException("Klucze nie zgadzają się.");
        }

        userService.save(user);
    }
}
