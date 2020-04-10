package com.vandemos.registerservice;

import com.vandemos.registerservice.validator.ValidatorService;
import com.vandemos.registerservice.validator.ValidatorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class ValidatorUtilsTests {

    ValidatorUtils validatorUtils = new ValidatorUtils();

    @Test
    public void passwordValidatorTest(){
        String password = "zaq1@WSX";

        boolean result = validatorUtils.validatePassword(password);

        assertTrue(result);
    }

    @Test
    public void passwodValidatorTest_notValidPassword(){
        String notValidPassword = "password";

        boolean result = validatorUtils.validatePassword(notValidPassword);

        assertFalse(result);
    }

    @Test
    public void passwordTheSameTest(){
        String pass = "somePass";
        String pass2 = "somePass";
        assertTrue(validatorUtils.passwordsTheSame(pass, pass2));
    }

    @Test
    public void passwordNotTheSameTest(){
        String pass = "somePass";
        String pass2 = "somePass1";
        assertFalse(validatorUtils.passwordsTheSame(pass, pass2));
    }

    @Test
    public void emailValidationTest(){
        String validEmail = "mail.asf23_sf@dd2.com";
        assertTrue(validatorUtils.validateEmail(validEmail));
    }

    @Test
    public void emailValidationTest_emailNotValid(){
        String notValidEmail = "afff@@d3.pl";
        assertFalse(validatorUtils.validateEmail(notValidEmail));
    }

    @Test
    public void usernameValidationTest(){
        String validUsername = "username12_";
        assertTrue(validatorUtils.validateUsername(validUsername));
    }

    @Test
    public void usernameValidationTest_usernameNotValid(){
        String validUsername = "userna&%me12_";
        assertFalse(validatorUtils.validateUsername(validUsername));
    }

    @Test
    public void phoneNumberValidationTest(){
        Integer validPhoneNumber = 123456789;
        assertTrue(validatorUtils.validatePhoneNumber(validPhoneNumber));
    }

    @Test
    public void phoneNumberValidationTest_phontNumberNotValid(){
        Integer notValidPhoneNumber = 1234567890;
        assertFalse(validatorUtils.validatePhoneNumber(notValidPhoneNumber));
    }
}
