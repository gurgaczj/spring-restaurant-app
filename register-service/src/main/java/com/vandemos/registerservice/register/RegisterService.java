package com.vandemos.registerservice.register;

import com.vandemos.registerservice.email.EmailService;
import com.vandemos.registerservice.model.RegisterModel;
import com.vandemos.registerservice.validator.ValidatorService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

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

        emailService.sendAccountActivationLink(registerModel.getEmail(), registerModel.getUser().getFirstName(), hash);

        return true;
    }


}
