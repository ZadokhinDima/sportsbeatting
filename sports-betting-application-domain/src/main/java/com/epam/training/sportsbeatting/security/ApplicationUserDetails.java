package com.epam.training.sportsbeatting.security;

import lombok.Getter;

import java.util.Collection;
import java.util.Collections;

import com.epam.training.sportsbeatting.domain.user.Admin;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUserDetails implements UserDetails {

    @Getter
    private User user;

    public ApplicationUserDetails(final User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user instanceof Player) {
            return Collections.singleton(new SimpleGrantedAuthority("PLAYER_ROLE"));
        }
        if (user instanceof Admin) {
            return Collections.singleton(new SimpleGrantedAuthority("ADMIN_ROLE"));
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
