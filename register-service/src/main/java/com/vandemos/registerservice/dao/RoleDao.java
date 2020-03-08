package com.vandemos.registerservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vandemos.registerservice.dto.RoleDto;
import com.vandemos.registerservice.model.RoleEnum;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "role")
public class RoleDao implements Dtoable<RoleDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Column(name = "rolename")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleEnum roleEnum;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<UserDao> user;

    public RoleDao(){}

    @Override
    public RoleDto toDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, RoleDto.class);
    }

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

    public List<UserDao> getUser() {
        return user;
    }

    public void setUser(List<UserDao> user) {
        this.user = user;
    }
}
