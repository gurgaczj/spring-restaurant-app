package com.vandemos.registerservice.dto;

import com.vandemos.registerservice.model.RoleEnum;

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
}
