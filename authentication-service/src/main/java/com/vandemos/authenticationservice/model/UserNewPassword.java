package com.vandemos.authenticationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserNewPassword {

    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
}
