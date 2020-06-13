package com.vandemos.authenticationservice.jwt;

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

        String key = "*F-JaNdRgUkXp2s5v8y/B?D(G+KbPeShVmYq3t6w9z$C&F)H@McQfTjWnZr4u7x!";
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("accessTokenExpirationTime"), 120);
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("refreshTokenExpirationTime"), 10080);
        FieldSetter.setField(jwtUtils, jwtUtils.getClass().getDeclaredField("signingKey"), key);
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

            String refreshToken = jwtUtils.generateRefreshToken(username, roles);
            assertNotNull(refreshToken);
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during token creation");
        }
    }

    @Test
    public void parseClaimsTest(){
        String username = "someUser";
        Collection<? extends GrantedAuthority> roles =
                Arrays.asList(new SimpleGrantedAuthority("ROLE1"), new SimpleGrantedAuthority("ROLE2"));
        try {
            String token = jwtUtils.generateAccessToken(username, roles);
            assertNotNull(jwtUtils.parseClaims(token));

            Collection<? extends GrantedAuthority> rolesFromToken = jwtUtils.getRoles(token);

            rolesFromToken.stream()
            .map(GrantedAuthority::getAuthority)
            .forEach(auth -> assertTrue(
                    roles.stream().map(GrantedAuthority::getAuthority)
                    .anyMatch(s -> s.equals(auth))
            ));

            String issuer = jwtUtils.getIssuer(token);
            assertEquals(issuer, username);



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

            Collection<? extends GrantedAuthority> rolesFromToken = jwtUtils.getRoles(token);

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
            assertNotNull(jwtUtils.parseClaims(token));

            String issuer = jwtUtils.getIssuer(token);
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
            assertNotNull(jwtUtils.parseClaims(token));

            Date expDate = jwtUtils.getExperienceDate(token);
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
            assertNotNull(jwtUtils.parseClaims(token));

            Date issuedDate = jwtUtils.getIssuedAtDate(token);
            assertTrue(issuedDate.before(new Date()));
        } catch (JwtException e){
            e.printStackTrace();
            fail("Error during claims parse");
        }
    }
}
