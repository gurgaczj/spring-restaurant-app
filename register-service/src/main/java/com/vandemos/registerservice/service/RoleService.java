package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.RoleDao;
import com.vandemos.registerservice.model.RoleEnum;
import com.vandemos.registerservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<RoleDao> findByRoleName(RoleEnum roleEnum) {
        return roleRepository.findByRoleEnum(roleEnum);
    }
}
