package com.vandemos.authenticationservice.jwt;

import com.google.gson.internal.LinkedTreeMap;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@EnableConfigurationProperties
@Service
public class JwtUtilsImpl implements JwtUtils {

    @Value("${jwt.access.expiration}")
    private Integer accessTokenExpirationTime;
    @Value("${jwt.refresh.expiration}")
    private Integer refreshTokenExpirationTime;
    @Value("${jwt.access.signing-key}")
    private String accessTokenSigningKey;
    @Value("${jwt.refresh.signing-key}")
    private String refreshTokenSigningKey;
    @Value("${jwt.audience}")
    private String audience;

    private String generateToken(String username, Collection<? extends GrantedAuthority> roles, int tokenTime,
                                 String subject, String signingKey) {
        JwtBuilder builder = Jwts.builder()
                .setAudience(audience)
                .setExpiration(getExpirationDate(tokenTime))
                .setIssuer(username)
                .setSubject(subject)
                .setIssuedAt(new Date());
        if (roles != null) {
            builder.claim("roles", roles);
        }
        return builder
                .signWith(Keys.hmacShaKeyFor(signingKey.getBytes()))
                .compact();
    }

    private Jws<Claims> parseClaims(String token, String signingKey) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(signingKey.getBytes()))
                .requireAudience(audience)
                .build().parseClaimsJws(token);
    }

    @Override
    public Jws<Claims> getAccessTokenClaims(String token) {
        return parseClaims(token, accessTokenSigningKey);
    }

    @Override
    public Jws<Claims> getRefreshTokenClaims(String token) {
        return parseClaims(token, refreshTokenSigningKey);
    }

    @Override
    public String getIssuer(Jws<Claims> claims) {
        return claims.getBody().getIssuer();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(Jws<Claims> claims) {
        Collection<SimpleGrantedAuthority> result = new HashSet<>();
        List<LinkedTreeMap> roleMaps =
                (List<LinkedTreeMap>) claims.getBody().get("roles");
        for (LinkedTreeMap map : roleMaps) {
            result.add(new SimpleGrantedAuthority((String) map.get("role")));
        }
        return result;
    }

    @Override
    public Date getExperienceDate(Jws<Claims> claims) {
        return claims.getBody().getExpiration();
    }

    @Override
    public Date getIssuedAtDate(Jws<Claims> claims) {
        return claims.getBody().getIssuedAt();
    }

    @Override
    public String generateAccessToken(String username, Collection<? extends GrantedAuthority> roles) {
        return generateToken(username, roles, accessTokenExpirationTime, "access-token", accessTokenSigningKey);
    }

    @Override
    public String generateRefreshToken(String username) {
        return generateToken(username, null, refreshTokenExpirationTime, "refresh-token", refreshTokenSigningKey);
    }

    private Date getExpirationDate(int tokenTime) {
        return Date.from(Instant.now().plusSeconds(tokenTime * 60));
    }
}
