package com.vandemos.registerservice.register;

import com.vandemos.registerservice.dao.RegistrationDao;
import com.vandemos.registerservice.dao.RoleDao;
import com.vandemos.registerservice.dao.UserDao;
import com.vandemos.registerservice.dao.UserInfoDao;
import com.vandemos.registerservice.exception.RegisterException;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.model.RoleEnum;
import com.vandemos.registerservice.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NewUserService {

    private AddressService addressService;
    private UserService userService;
    private UserInfoService userInfoService;
    private RoleService roleService;
    private RegistrationService registrationService;
    private PasswordEncoder passwordEncoder;

    public NewUserService(AddressService addressService, UserService userService, UserInfoService userInfoService, RoleService roleService, RegistrationService registrationService, PasswordEncoder passwordEncoder) {
        this.addressService = addressService;
        this.userService = userService;
        this.userInfoService = userInfoService;
        this.roleService = roleService;
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveNewUser(RegisterModel registerModel, String hash) {
        if (usernameExist(registerModel.getUsername())) {
            throw new RegisterException("Nazwa użytkownika jest już zajęta");
        }

        if (emailExist(registerModel.getEmail())) {
            throw new RegisterException("Adres email jest już zajęty");
        }

        UserDao userDao = new UserDao();
        userDao.setUsername(registerModel.getUsername());
        userDao.setEmail(registerModel.getEmail());
        userDao.setPassword(passwordEncoder.encode(registerModel.getPassword()));
        userDao.setActivated(false);
        userDao.setEnabled(false);

        UserInfoDao userInfoDao = registerModel.getUser().toDao();
        userInfoDao.setUser(null);
        userInfoDao.getAddress().setUser(null);

        RegistrationDao registrationDao = new RegistrationDao();
        registrationDao.setRegistrationDate(LocalDateTime.now());
        registrationDao.setHash(hash);
        registrationDao.setActivationDate(null);

        Optional<RoleDao> roleDao = roleService.findByRoleName(RoleEnum.USER);
        userDao.setRole(roleDao.orElseThrow(() -> {
            //TODO: log error
            return new RegisterException("Coś poszło nie tak. Spróbuj później");
        }));
        userDao.setUserInfo(userInfoDao);
        userDao.setRegistration(registrationDao);

        Optional<UserDao> optionalUserDao = userService.save(userDao);

        return optionalUserDao.isPresent();
    }

    public boolean usernameExist(String username) {
        return userService.findByUsername(username).isPresent();
    }

    public boolean emailExist(String email) {
        return userService.findByEmail(email).isPresent();
    }
}
