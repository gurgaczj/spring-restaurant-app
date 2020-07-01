package com.vandemos.authenticationservice.validator;

public interface ValidatorUtils {

    boolean validatePassword(String password);

    boolean passwordsTheSame(String password, String confirmPassword);


}
