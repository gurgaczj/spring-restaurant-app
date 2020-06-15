package com.vandemos.registerservice.dto;

import com.vandemos.registerservice.dao.Role;
import com.vandemos.registerservice.model.RoleEnum;
import org.modelmapper.ModelMapper;

public class RoleDto {

    public RoleEnum roleEnum;

    public RoleDto(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public RoleDto() {
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public Role toDao() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Role.class);
    }
}
