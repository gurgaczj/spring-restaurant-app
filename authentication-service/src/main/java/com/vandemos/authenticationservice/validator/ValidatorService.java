package com.vandemos.authenticationservice.validator;

import com.vandemos.authenticationservice.exception.ValidationException;
import com.vandemos.authenticationservice.model.UserNewPassword;

public interface ValidatorService {

    boolean validateNewPassword(UserNewPassword userNewPassword);

    default boolean validateHashedPasswordsTheSame(String actualPassword, String passwordFromRequest){
        if(!actualPassword.equals(passwordFromRequest)){
            throw new ValidationException("Stare hasła nie pasują.");
        }
        return true;
    }
}
