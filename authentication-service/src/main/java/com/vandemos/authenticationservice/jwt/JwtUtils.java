package com.vandemos.authenticationservice.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface JwtUtils {

    String generateJwt(String username, Collection<? extends GrantedAuthority> roles);
}
