package com.vandemos.registerservice.model;

import lombok.Data;

@Data
public class RegisterModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
