package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.RoleDao;
import com.vandemos.registerservice.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleDao, Long> {

    Optional<RoleDao> findByRoleEnum(@NotNull RoleEnum roleEnum);
}
