package com.vandemos.registerservice.repository;

import com.vandemos.registerservice.dao.UserInfoDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfoDao, Long> {
}
