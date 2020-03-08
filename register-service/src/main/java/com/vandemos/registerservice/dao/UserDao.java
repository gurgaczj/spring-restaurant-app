package com.vandemos.registerservice.dao;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "user")
@Data
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
}
