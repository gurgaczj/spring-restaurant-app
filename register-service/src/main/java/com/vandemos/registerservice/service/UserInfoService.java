package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.UserInfo;
import com.vandemos.registerservice.repository.UserInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class UserInfoService implements IUserInfoService<UserInfo, Long> {

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo findById(Long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() -> new UserInfoNotFound("UserInfo with id " + id + " was not found"));
    }

    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public Iterable<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private final static class UserInfoNotFound extends RuntimeException {
        public UserInfoNotFound(String message) {
            super(message);
        }
    }
}
