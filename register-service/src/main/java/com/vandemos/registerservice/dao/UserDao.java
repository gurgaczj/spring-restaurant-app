package com.vandemos.registerservice.dao;

import com.vandemos.registerservice.dto.UserDto;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "user")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @NotNull
    @Column
    private String username;
    @NotNull
    @Column
    private String password;
    @NotNull
    @Column
    private String email;
    @NotNull
    @Column(name = "is_activated")
    private boolean isActivated;
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleDao role;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_info_id", referencedColumnName = "id")
    private RegistrationDao registration;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private UserInfoDao userInfo;

    public UserDao(){}

    public UserDto toDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, UserDto.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoleDao getRole() {
        return role;
    }

    public void setRole(RoleDao role) {
        this.role = role;
    }

    public RegistrationDao getRegistration() {
        return registration;
    }

    public void setRegistration(RegistrationDao registration) {
        this.registration = registration;
    }

    public UserInfoDao getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDao userInfo) {
        this.userInfo = userInfo;
    }
}
