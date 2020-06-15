package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.Registration;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<Registration, Long> {
}
