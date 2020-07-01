package com.vandemos.authenticationservice.forgotpassword;

import com.vandemos.authenticationservice.model.UserNewPassword;

public interface ForgotPasswordService {

    boolean changePassword(UserNewPassword userNewPassword);
}
