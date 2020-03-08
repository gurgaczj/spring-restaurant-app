package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Long> {
}
