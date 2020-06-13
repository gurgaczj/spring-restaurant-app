package com.vandemos.authenticationservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

@EnableConfigurationProperties
@Service
public class JwtUtilsImpl implements JwtUtils {

    @Value("${jwt.expiration}")
    private Integer daysToExpire;

    @Value("${jwt.signing-key}")
    private String signingKey;

    public String generateJwt(String username, Collection<? extends GrantedAuthority> roles) {
        return Jwts.builder()
                .setAudience("restaurant app")
                .setExpiration(getExpirationDate())
                .setIssuer(username)
                .setSubject("auth")
                .setIssuedAt(new Date())
                .claim("roles", roles)
                .signWith(Keys.hmacShaKeyFor(signingKey.getBytes()))
                .compact();
    }

    private Date getActualDate() {
        return toDate(LocalDateTime.now());
    }

    private Date getExpirationDate() {
        LocalDateTime expiration = LocalDateTime.now().plusDays(this.daysToExpire);
        return toDate(expiration);
    }

    private Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
