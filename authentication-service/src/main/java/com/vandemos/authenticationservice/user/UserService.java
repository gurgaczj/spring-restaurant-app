package com.vandemos.authenticationservice.user;

import java.util.Optional;

public interface UserService {

    User findById(Long id);
    User findUserByUsername(String username);
}
