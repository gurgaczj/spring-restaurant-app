package com.vandemos.authenticationservice.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorUtilsImplTests {

    private final ValidatorUtils validator = new ValidatorUtilsImpl();

    @Test
    public void validatePasswordTest(){
        String someValidPassword = "Password1@";
        assertTrue(validator.validatePassword(someValidPassword));
    }

    @Test
    public void validatePasswordTest_passwordIsNotValid(){
        String someNotValidPassword = "pass";
        assertFalse(validator.validatePassword(someNotValidPassword));
    }

    @Test
    public void passwordsTheSameTest(){
        String pass1 = "pass";
        String pass2 = "pass";
        assertTrue(validator.passwordsTheSame(pass1, pass2));
    }

    @Test
    public void passwordsTheSameTest_passwordsNotTheSame(){
        String pass1 = "pass";
        String pass2 = "pas124s";
        assertFalse(validator.passwordsTheSame(pass1, pass2));
    }
}
