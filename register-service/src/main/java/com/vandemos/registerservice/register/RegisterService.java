package com.vandemos.registerservice.register;

import com.vandemos.registerservice.email.EmailServiceClient;
import com.vandemos.registerservice.exception.ConfirmationException;
import com.vandemos.registerservice.model.Mail;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.validator.ValidatorService;
import com.vandemos.registerservice.validator.ValidatorServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    private final ValidatorService validatorService;
    private final NewUserService newUserService;
    private final EmailServiceClient emailServiceClient;

    public RegisterService(ValidatorService validatorService, NewUserService newUserService, EmailServiceClient emailServiceClient) {
        this.validatorService = validatorService;
        this.newUserService = newUserService;
        this.emailServiceClient = emailServiceClient;
    }

    public boolean registerUser(RegisterModel registerModel) {
        validatorService.validate(registerModel.getUsername(), registerModel.getEmail(), registerModel.getPassword(),
                registerModel.getConfirmPassword(), registerModel.getUser().getPhoneNumber());

        String hash = RandomStringUtils.randomAlphabetic(30);

        if (!newUserService.saveNewUser(registerModel, hash)) {
            return false;
        }

        emailServiceClient.sendActivationMail(new Mail(registerModel.getEmail(), registerModel.getUsername(),
                registerModel.getUser().getFirstName(), hash));

        return true;
    }

    public void confirmAccount(Optional<String> optUsername, Optional<String> optHash) {
        String username = optUsername.orElseThrow(() -> new ConfirmationException("Nie podano nazwy użytkownika"));
        String hash = optHash.orElseThrow(() -> new ConfirmationException("Nie podano klucza"));


        newUserService.confirmAccount(username, hash);
    }

}
