package com.vandemos.authenticationservice.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AppUserDetails implements UserDetails {

    private User user;

    public AppUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleEnum().name()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }
}
