package com.vandemos.authenticationservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtUtilsTests {

    private JwtUtils jwtUtils;

    @SneakyThrows
    @BeforeEach
    private void initTest(){
        jwtUtils = new JwtUtilsImpl();

        String accessKey = "*F-JaNdRgUkXp2s5v8y/B?D(G+KbPeShVmYq3t6w9z$C&F)H@McQfTjWnZr4u7x!";
        String refreshKey = "w!z%C*F-JaNdRfUjXn2r5u8x/A?D(G+KbPeShVkYp3s6v9y$B&E)H@McQfTjWnZq";
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("accessTokenExpirationTime"), 120);
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("refreshTokenExpirationTime"), 10080);
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("accessTokenSigningKey"), accessKey);
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("refreshTokenSigningKey"), refreshKey);
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("appName"), "some-app");
    }

    @Test
    public void generateTokenTest(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String accessToken = jwtUtils.generateAccessToken(username, roles);
            assertNotNull(accessToken);

            String refreshToken = jwtUtils.generateRefreshToken(username);
            assertNotNull(refreshToken);
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during token creation");
        }
    }

    @Test
    public void parseAccessTokenClaimsTest(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String token = jwtUtils.generateAccessToken(username, roles);

            Jws<Claims> claims = jwtUtils.getAccessTokenClaims(token);
            assertNotNull(claims);
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during claims parse");
        }
    }

    @Test
    public void parseRefreshTokenClaimsTest(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String token = jwtUtils.generateRefreshToken(username);

            Jws<Claims> claims = jwtUtils.getRefreshTokenClaims(token);
            assertNotNull(claims);
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during claims parse");
        }
    }

    @Test
    public void getRolesFromTokenTest(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String token = jwtUtils.generateAccessToken(username, roles);
            Jws<Claims> claims = jwtUtils.getAccessTokenClaims(token);

            Collection<? extends GrantedAuthority> rolesFromToken = jwtUtils.getRoles(claims);

            rolesFromToken.stream()
                    .map(GrantedAuthority::getAuthority)
                    .forEach(auth -> assertTrue(
                            roles.stream().map(GrantedAuthority::getAuthority)
                                    .anyMatch(s -> s.equals(auth))
                    ));
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during claims parse");
        }
    }

    @Test
    public void getIssuerFromTokenTest(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String token = jwtUtils.generateAccessToken(username, roles);
            Jws<Claims> claims = jwtUtils.getAccessTokenClaims(token);
            assertNotNull(claims);

            String issuer = jwtUtils.getIssuer(claims);
            assertEquals(issuer, username);

        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during claims parse");
        }
    }

    @Test
    public void checkExpDateTest(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String token = jwtUtils.generateAccessToken(username, roles);
            Jws<Claims> claims = jwtUtils.getAccessTokenClaims(token);
            assertNotNull(claims);

            Date expDate = jwtUtils.getExperienceDate(claims);
            assertTrue(expDate.after(new Date()));
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during claims parse");
        }
    }

    @Test
    public void isIssuedDateBeforeNow(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String token = jwtUtils.generateAccessToken(username, roles);
            Jws<Claims> claims = jwtUtils.getAccessTokenClaims(token);
            assertNotNull(claims);

            Date issuedDate = jwtUtils.getIssuedAtDate(claims);
            assertTrue(issuedDate.before(new Date()));
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during claims parse");
        }
    }
}
