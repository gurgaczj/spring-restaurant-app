package com.vandemos.registerservice.dto;

import com.vandemos.registerservice.dao.AddressDao;
import com.vandemos.registerservice.dao.RoleDao;
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

    public RoleDao toDao() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, RoleDao.class);
    }
}
