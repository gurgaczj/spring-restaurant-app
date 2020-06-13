package com.vandemos.authenticationservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

public interface JwtUtils {

    String generateAccessToken(String username, Collection<? extends GrantedAuthority> roles);

    String generateRefreshToken(String username, Collection<? extends GrantedAuthority> roles);

    Jws<Claims> parseClaims(String token);

    String getIssuer(String token);

    Collection<? extends GrantedAuthority> getRoles(String token);

    Date getExperienceDate(String token);

    Date getIssuedAtDate(String token);
}
