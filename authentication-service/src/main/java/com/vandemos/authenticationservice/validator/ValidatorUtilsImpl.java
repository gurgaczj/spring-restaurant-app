package com.vandemos.authenticationservice.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidatorUtilsImpl implements ValidatorUtils {

    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=_-])(?=\\S+$).{8,16}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean passwordsTheSame(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
