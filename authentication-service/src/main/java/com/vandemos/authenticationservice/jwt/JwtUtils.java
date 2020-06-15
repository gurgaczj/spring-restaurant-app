package com.vandemos.authenticationservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

public interface JwtUtils {

    String generateAccessToken(String username, Collection<? extends GrantedAuthority> roles);

    String generateRefreshToken(String username);

    Jws<Claims> getAccessTokenClaims(String token);
    Jws<Claims> getRefreshTokenClaims(String token);

    String getIssuer(Jws<Claims> claims);

    Collection<? extends GrantedAuthority> getRoles(Jws<Claims> claims);

    Date getExperienceDate(Jws<Claims> claims);

    Date getIssuedAtDate(Jws<Claims> claims);
}
