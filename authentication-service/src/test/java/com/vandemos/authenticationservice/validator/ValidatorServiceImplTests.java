package com.vandemos.authenticationservice.validator;

import com.vandemos.authenticationservice.exception.ValidationException;
import com.vandemos.authenticationservice.model.UserNewPassword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ValidatorServiceImplTests {

    @Mock
    private ValidatorUtils validatorUtils;

    @InjectMocks
    private ValidatorServiceImpl validatorService;

    @Test
    public void validateNewPasswordTest(){
        UserNewPassword userNewPassword = new UserNewPassword("username", "oldPass", "newPass", "newPass");

        Mockito.when(validatorUtils.validatePassword(userNewPassword.getPassword())).thenReturn(true);
        Mockito.when(validatorUtils
                .passwordsTheSame(userNewPassword.getPassword(), userNewPassword.getConfirmPassword())).thenReturn(true);

        assertTrue(validatorService.validateNewPassword(userNewPassword));
    }

    @Test
    public void validateNewPasswordTest_newPasswordNotMeetsRequirements(){
        UserNewPassword userNewPassword = new UserNewPassword("username", "oldPass", "newPass", "newPass");

        Mockito.when(validatorUtils.validatePassword(userNewPassword.getPassword())).thenReturn(false);

        assertThrows(ValidationException.class, () -> validatorService.validateNewPassword(userNewPassword));
    }

    @Test
    public void validateNewPasswordTest_passwordsNotTheSame(){
        UserNewPassword userNewPassword = new UserNewPassword("username", "oldPass", "newPass", "newPass");

        Mockito.when(validatorUtils.validatePassword(userNewPassword.getPassword())).thenReturn(true);
        Mockito.when(validatorUtils
                .passwordsTheSame(userNewPassword.getPassword(), userNewPassword.getConfirmPassword())).thenReturn(false);

        assertThrows(ValidationException.class, () -> validatorService.validateNewPassword(userNewPassword));
    }

    @Test
    public void validateHashedPasswordsTheSameTest(){
        String pass1 = "pass";
        String pass2 = "pass";

        assertTrue(validatorService.validateHashedPasswordsTheSame(pass1, pass2));
    }

    @Test
    public void validateHashedPasswordsTheSameTest_passwordsNotTheSame(){
        String pass1 = "pass";
        String pass2 = "paarg34t3ss";

        assertThrows(ValidationException.class, () -> validatorService.validateHashedPasswordsTheSame(pass1, pass2));
    }
}
