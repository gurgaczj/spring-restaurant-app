package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.RoleDao;
import com.vandemos.registerservice.model.RoleEnum;
import com.vandemos.registerservice.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class RoleService implements IRoleService<RoleDao, Long> {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public RoleDao findByRoleEnum(RoleEnum roleEnum) {
        return roleRepository.findByRoleEnum(roleEnum)
                .orElseThrow(() -> new RoleNotFound("Role with name " + roleEnum.name() + " was not found"));
    }

    @Override
    public RoleDao findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFound("Role with id " + id + " was not found"));
    }

    @Override
    public RoleDao save(RoleDao entity) {
        return roleRepository.save(entity);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private final static class RoleNotFound extends RuntimeException {

        public RoleNotFound(String message) {
            super(message);
        }
    }
}
