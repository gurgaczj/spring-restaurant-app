package com.vandemos.authenticationservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vandemos.authenticationservice.jwt.JwtUtils;
import com.vandemos.authenticationservice.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class AppAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public AppAuthFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Login login;
        try {
            login = new ObjectMapper().readValue(request.getInputStream(), Login.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String jwtToken = jwtUtils.generateJwt(authResult.getName(), new ArrayList<>(authResult.getAuthorities()));
        response.setHeader("Authorization", "Bearer " + jwtToken);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Zły login lub hasło");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
