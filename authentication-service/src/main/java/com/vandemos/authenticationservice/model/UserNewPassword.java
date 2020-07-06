package com.vandemos.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNewPassword {

    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
}
