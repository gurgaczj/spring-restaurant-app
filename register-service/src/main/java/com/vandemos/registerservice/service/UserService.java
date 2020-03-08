package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.UserDao;
import com.vandemos.registerservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDao getById(Long id){
        return userRepository.findById(id).orElseThrow();
    }
}
