package com.vandemos.registerservice.service;

import com.vandemos.registerservice.dao.User;
import com.vandemos.registerservice.exception.UserNotFoundException;
import com.vandemos.registerservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService<User, Long> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " was not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email " + email + " was not found."));
    }
}
