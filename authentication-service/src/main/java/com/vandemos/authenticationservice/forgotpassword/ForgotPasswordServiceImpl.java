package com.vandemos.authenticationservice.forgotpassword;

import com.vandemos.authenticationservice.model.UserNewPassword;
import com.vandemos.authenticationservice.user.User;
import com.vandemos.authenticationservice.user.UserService;
import com.vandemos.authenticationservice.validator.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final static Logger logger = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);

    private final UserService userService;
    private final ValidatorService validatorService;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordServiceImpl(UserService userService, ValidatorService validatorService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.validatorService = validatorService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean changePassword(UserNewPassword userNewPassword) {
        validatorService.validateNewPassword(userNewPassword);

        User user = userService.findUserByUsername(userNewPassword.getUsername());

        String hashedOldPassword = passwordEncoder.encode(userNewPassword.getOldPassword());

        validatorService.validateHashedPasswordsTheSame(user.getPassword(), hashedOldPassword);

        user.setPassword(hashedOldPassword);
        userService.save(user);

        String logInfo = "User " + userNewPassword.getUsername() + " changed password";
        logger.info(logInfo);

        return true;
    }
}
