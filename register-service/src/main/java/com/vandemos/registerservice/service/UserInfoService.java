package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.UserInfoDao;
import com.vandemos.registerservice.repository.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Service
public class UserInfoService implements IUserInfoService<UserInfoDao, Long> {

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfoDao findById(Long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() -> new UserInfoNotFound("UserInfo with id " + id + " was not found"));
    }

    public UserInfoDao save(UserInfoDao userInfoDao) {
        return userInfoRepository.save(userInfoDao);
    }

    @Override
    public Iterable<UserInfoDao> findAll() {
        return userInfoRepository.findAll();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private final static class UserInfoNotFound extends RuntimeException {
        public UserInfoNotFound(String message) {
            super(message);
        }
    }
}
