package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.UserDao;
import com.vandemos.registerservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDao getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Optional<UserDao> save(UserDao userDao) {
        return Optional.of(userRepository.save(userDao));
    }

    public Optional<UserDao> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserDao> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
