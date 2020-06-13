package com.vandemos.registerservice;

import com.vandemos.registerservice.validator.ValidatorUtils;
import com.vandemos.registerservice.validator.ValidatorUtilsImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class ValidatorUtilsTests {

    @Test
    public void passwordValidatorTest(){
        ValidatorUtils validatorUtils = new ValidatorUtilsImpl();

        String password = "zaq1@WSX";

        boolean result = validatorUtils.validatePassword(password);

        assertTrue(result);
    }

    @Test
    public void passwodValidatorTest_notValidPassword(){
        ValidatorUtils validatorUtils = new ValidatorUtilsImpl();

        String notValidPassword = "password";

        boolean result = validatorUtils.validatePassword(notValidPassword);

        assertFalse(result);
    }
}
