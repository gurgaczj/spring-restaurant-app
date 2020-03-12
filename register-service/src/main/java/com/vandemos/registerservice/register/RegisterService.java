package com.vandemos.registerservice.register;

import com.vandemos.registerservice.email.EmailService;
import com.vandemos.registerservice.exception.ConfirmationException;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.validator.ValidatorService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    private ValidatorService validatorService;
    private NewUserService newUserService;
    private EmailService emailService;

    public RegisterService(ValidatorService validatorService, NewUserService newUserService, EmailService emailService) {
        this.validatorService = validatorService;
        this.newUserService = newUserService;
        this.emailService = emailService;
    }

    public boolean registerUser(RegisterModel registerModel) {
        validatorService.validate(registerModel.getUsername(), registerModel.getEmail(), registerModel.getPassword(),
                registerModel.getConfirmPassword(), registerModel.getUser().getPhoneNumber());

        String hash = RandomStringUtils.randomAlphabetic(30);

        if (!newUserService.saveNewUser(registerModel, hash)) {
            return false;
        }

        emailService.sendAccountActivationLink(registerModel.getEmail(), registerModel.getUser().getFirstName(), hash,
                registerModel.getUsername());

        return true;
    }

    public void confirmAccount(Optional<String> optUsername, Optional<String> optHash) {
        String username = optUsername.orElseThrow(() -> new ConfirmationException("Nie podano nazwy użytkownika"));
        String hash = optHash.orElseThrow(() -> new ConfirmationException("Nie podano klucza"));


        newUserService.confirmAccount(username, hash);
    }

}
