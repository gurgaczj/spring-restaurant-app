package com.vandemos.authenticationservice.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vandemos.authenticationservice.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Column(name = "rolename")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleEnum roleEnum;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> user;

    public Role(Long id, RoleEnum roleEnum, List<User> user) {
        this.id = id;
        this.roleEnum = roleEnum;
        this.user = user;
    }

    public Role(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}