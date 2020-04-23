package com.vandemos.registerservice.validator;

public interface ValidatorUtils {

    boolean validatePassword(String password);

    boolean passwordsTheSame(String password, String confirmPassword);

    boolean validatePhoneNumber(Integer phoneNumber);

    boolean validateEmail(String email);

    boolean validateUsername(String username);
}
