package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.UserInfoDao;
import com.vandemos.registerservice.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public Iterable<UserInfoDao> getAll() {
        return userInfoRepository.findAll();
    }

    public UserInfoDao getById(Long id) {
        //TODO: throw exception
        return userInfoRepository.findById(id).orElseThrow();
    }

    public Optional<UserInfoDao> save(UserInfoDao userInfoDao) {
        return Optional.of(userInfoRepository.save(userInfoDao));
    }
}
