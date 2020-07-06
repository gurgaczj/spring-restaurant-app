package com.vandemos.authenticationservice.validator;

import com.vandemos.authenticationservice.exception.ValidationException;
import com.vandemos.authenticationservice.model.UserNewPassword;
import org.springframework.stereotype.Service;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    private final ValidatorUtils validatorUtils;

    public ValidatorServiceImpl(ValidatorUtils validatorUtils) {
        this.validatorUtils = validatorUtils;
    }

    @Override
    public boolean validateNewPassword(UserNewPassword userNewPassword) {
        if (!validatorUtils.validatePassword(userNewPassword.getPassword())) {
            throw new ValidationException("Hasło nie spełnia wymagań bezpieczeństwa.");
        }

        if (!validatorUtils.passwordsTheSame(userNewPassword.getPassword(), userNewPassword.getConfirmPassword())) {
            throw new ValidationException("Hasła nie są takie same.");
        }
        return true;
    }
}
