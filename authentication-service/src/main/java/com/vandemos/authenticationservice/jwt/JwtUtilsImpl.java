package com.vandemos.authenticationservice.jwt;

import com.google.gson.internal.LinkedTreeMap;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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
    @Value("${jwt.signing-key}")
    private String signingKey;
    @Value("${spring.application.name}")
    private String appName;

    private String generateToken(String username, Collection<? extends GrantedAuthority> roles, int tokenTime, String subject) {
        return Jwts.builder()
                .setAudience(appName)
                .setExpiration(getExpirationDate(tokenTime))
                .setIssuer(username)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .claim("roles", roles)
                .signWith(Keys.hmacShaKeyFor(signingKey.getBytes()))
                .compact();
    }

    public Jws<Claims> parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(signingKey.getBytes()))
                .requireAudience(appName)
                .build().parseClaimsJws(token);
    }

    @Override
    public String getIssuer(String token) {
        return parseClaims(token).getBody().getIssuer();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) {
        Collection<SimpleGrantedAuthority> result = new HashSet<>();
        List<LinkedTreeMap> roleMaps =
                (List<LinkedTreeMap>) parseClaims(token).getBody().get("roles");
        for(LinkedTreeMap map : roleMaps){
            result.add(new SimpleGrantedAuthority((String) map.get("role")));
        }
        return result;
    }

    @Override
    public Date getExperienceDate(String token) {
        return parseClaims(token).getBody().getExpiration();
    }

    @Override
    public Date getIssuedAtDate(String token) {
        return parseClaims(token).getBody().getIssuedAt();
    }

    @Override
    public String generateAccessToken(String username, Collection<? extends GrantedAuthority> roles) {
        return generateToken(username, roles, accessTokenExpirationTime, "access-token");
    }

    @Override
    public String generateRefreshToken(String username, Collection<? extends GrantedAuthority> roles) {
        return generateToken(username, roles, refreshTokenExpirationTime, "refresh-token");
    }

    private Date getExpirationDate(int tokenTime) {
        return Date.from(Instant.now().plusSeconds(tokenTime * 60));
    }
}
