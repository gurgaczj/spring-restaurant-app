package com.vandemos.registerservice.model;

import com.vandemos.registerservice.dto.UserInfoDto;
import lombok.Data;

public class RegisterModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private UserInfoDto user;

    public RegisterModel() {}

    public RegisterModel(String username, String password, String confirmPassword, String email, UserInfoDto user) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfoDto getUser() {
        return user;
    }

    public void setUser(UserInfoDto user) {
        this.user = user;
    }
}
