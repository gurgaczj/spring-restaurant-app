package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.Role;
import com.vandemos.registerservice.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByRoleEnum(@NotNull RoleEnum roleEnum);
}
