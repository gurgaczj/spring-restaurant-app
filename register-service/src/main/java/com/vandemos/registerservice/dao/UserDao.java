package com.vandemos.registerservice.dao;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "user")
@Data
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "is_activated")
    private boolean isActivated;
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleDao role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_info_id", referencedColumnName = "id")
    private RegistrationDao registration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private UserInfoDao userInfo;
}
