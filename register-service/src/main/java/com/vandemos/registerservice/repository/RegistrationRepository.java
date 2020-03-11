package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.RegistrationDao;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<RegistrationDao, Long> {
}
