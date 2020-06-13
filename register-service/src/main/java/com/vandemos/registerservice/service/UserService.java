package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.UserDao;
import com.vandemos.registerservice.exception.UserNotFoundException;
import com.vandemos.registerservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService<UserDao, Long> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDao findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDao save(UserDao userDao) {
        return userRepository.save(userDao);
    }

    public UserDao findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " was not found"));
    }

    public UserDao findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " was not found."));
    }
}
