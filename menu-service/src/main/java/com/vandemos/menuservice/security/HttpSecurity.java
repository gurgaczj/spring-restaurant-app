package com.vandemos.menuservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class HttpSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/menu", "/menu/**").permitAll().anyRequest().authenticated();
    }
}
