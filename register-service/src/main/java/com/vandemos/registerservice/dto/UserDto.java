package com.vandemos.registerservice.dto;

public class UserDto {

    private String username;
    private String email;
    private boolean isActivated;
    private boolean isEnabled;
    private RoleDto role;
    private RegistrationDto registration;
    private UserInfoDto userInfo;

    public UserDto(){}

    public UserDto(String username, String email, boolean isActivated, boolean isEnabled, RoleDto role, RegistrationDto registration, UserInfoDto userInfo) {
        this.username = username;
        this.email = email;
        this.isActivated = isActivated;
        this.isEnabled = isEnabled;
        this.role = role;
        this.registration = registration;
        this.userInfo = userInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public RegistrationDto getRegistration() {
        return registration;
    }

    public void setRegistration(RegistrationDto registration) {
        this.registration = registration;
    }

    public UserInfoDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDto userInfo) {
        this.userInfo = userInfo;
    }
}
