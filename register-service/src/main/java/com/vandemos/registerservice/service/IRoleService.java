package com.vandemos.registerservice.service;

import com.vandemos.registerservice.model.RoleEnum;
import org.springframework.stereotype.Service;

@Service
public interface IRoleService<T, ID> extends BaseService<T, ID> {

    T findByRoleEnum(RoleEnum roleName);
}
