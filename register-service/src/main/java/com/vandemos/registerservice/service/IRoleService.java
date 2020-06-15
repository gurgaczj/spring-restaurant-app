package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.RoleDao;
import com.vandemos.registerservice.model.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IRoleService<T, ID> extends BaseService<T, ID> {

    T findByRoleEnum(RoleEnum roleName);
}
