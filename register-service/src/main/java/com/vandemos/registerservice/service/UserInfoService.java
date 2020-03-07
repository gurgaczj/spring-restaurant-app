package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.UserInfoDao;
import com.vandemos.registerservice.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private UserInfoRepository userInfoRepository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public Iterable<UserInfoDao> getAll(){
        return userInfoRepository.findAll();
    }

    public UserInfoDao getById(Long id){
        //TODO: throw exception
        return userInfoRepository.findById(id).orElseThrow();
    }
}
