package com.vandemos.authenticationservice.validator;

import com.vandemos.authenticationservice.exception.ValidationException;
import com.vandemos.authenticationservice.model.UserNewPassword;

public interface ValidatorService {

    boolean validateNewPassword(UserNewPassword userNewPassword);

    default boolean validatePasswordsTheSame(String password, String confirmPassword){
        if(!password.equals(confirmPassword)){
            throw new ValidationException("Hasła nie są takie same.");
        }
        return true;
    }
}
