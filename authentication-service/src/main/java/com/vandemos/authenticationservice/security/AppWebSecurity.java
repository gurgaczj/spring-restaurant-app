package com.vandemos.authenticationservice.security;

import com.vandemos.authenticationservice.jwt.JwtUtils;
import com.vandemos.authenticationservice.user.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class AppWebSecurity extends WebSecurityConfigurerAdapter {

    private final AppUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    //private AppAuthFilter appAuthFilter;



    public AppWebSecurity(AppUserDetailsService userDetailsService,
                          // , AppAuthFilter appAuthFilter
                          JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        //this.appAuthFilter = appAuthFilter;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.formLogin().usernameParameter("username").passwordParameter("password")
        .successHandler((request, response, authentication) -> {
            handleSuccess(response, authentication);
        })
        .failureHandler((request, response, exception) -> {
            response.sendError(HttpStatus.BAD_REQUEST.value(),
                    exception.getMessage());
        });
    }

    private void handleSuccess(HttpServletResponse response, Authentication authentication) {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.AUTHORIZATION,
                String.join("", "Bearer ",
                        jwtUtils.generateAccessToken(authentication.getName(), authentication.getAuthorities())));
        response.setHeader("Refresh-Token",
                String.join("", "Bearer ",
                        jwtUtils.generateRefreshToken(authentication.getName(), authentication.getAuthorities())));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
