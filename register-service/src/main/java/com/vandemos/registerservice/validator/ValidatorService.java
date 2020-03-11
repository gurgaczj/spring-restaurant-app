package com.vandemos.registerservice.validator;

import com.vandemos.registerservice.exception.RegisterException;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    private ValidatorUtils validatorUtils;

    public ValidatorService(ValidatorUtils validatorUtils) {
        this.validatorUtils = validatorUtils;
    }

    public void validate(String username, String email, String password, String conformPassword, Integer phoneNumber){

        if(!validatorUtils.validateUsername(username)){
            throw new RegisterException("Nazwa użytkownika nie spełnia wymagań.");
        }

        if(!validatorUtils.validateEmail(email)){
            throw new RegisterException("Format emaila nie spełnia wymagań.");
        }

        if(!validatorUtils.validatePassword(password)){
            throw new RegisterException("Hasło nie spełnia wymagań bezpieczeństwa.");
        }

        if(!validatorUtils.passwordsTheSame(password, conformPassword)){
            throw new RegisterException("Hasła nie są takie same.");
        }

        if(!validatorUtils.validatePhoneNumber(phoneNumber)){
            throw new RegisterException("Zły format numeru telefonu.");
        }
    }
}
