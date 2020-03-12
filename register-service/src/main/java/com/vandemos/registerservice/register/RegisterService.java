package com.vandemos.registerservice.register;

import com.vandemos.registerservice.email.EmailClient;
import com.vandemos.registerservice.exception.ConfirmationException;
import com.vandemos.registerservice.model.Mail;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.validator.ValidatorService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    private ValidatorService validatorService;
    private NewUserService newUserService;
    private EmailClient emailClient;

    public RegisterService(ValidatorService validatorService, NewUserService newUserService, EmailClient emailClient) {
        this.validatorService = validatorService;
        this.newUserService = newUserService;
        this.emailClient = emailClient;
    }

    public boolean registerUser(RegisterModel registerModel) {
        validatorService.validate(registerModel.getUsername(), registerModel.getEmail(), registerModel.getPassword(),
                registerModel.getConfirmPassword(), registerModel.getUser().getPhoneNumber());

        String hash = RandomStringUtils.randomAlphabetic(30);

        if (!newUserService.saveNewUser(registerModel, hash)) {
            return false;
        }

        emailClient.sendActivationMail(new Mail(registerModel.getEmail(), registerModel.getUsername(),
                registerModel.getUser().getFirstName(), hash));

        return true;
    }

    public void confirmAccount(Optional<String> optUsername, Optional<String> optHash) {
        String username = optUsername.orElseThrow(() -> new ConfirmationException("Nie podano nazwy użytkownika"));
        String hash = optHash.orElseThrow(() -> new ConfirmationException("Nie podano klucza"));


        newUserService.confirmAccount(username, hash);
    }

}
