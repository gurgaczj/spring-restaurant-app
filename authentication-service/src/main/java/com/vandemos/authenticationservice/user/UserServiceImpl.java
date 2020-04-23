package com.vandemos.authenticationservice.user;

import com.vandemos.authenticationservice.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " was not found."));
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " was not found"));
    }

}
