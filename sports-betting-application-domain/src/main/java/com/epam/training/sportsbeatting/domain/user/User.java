package com.epam.training.sportsbeatting.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@Entity
public abstract class User extends PersistableObject implements UserDetails {

    private String username;
    private String password;
    @ManyToMany
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserGroup> userGroups;
    private boolean enabled;

    public User(final Long id, final String username, final String password, final List<UserGroup> userGroups,
                final boolean enabled) {
        super(id);
        this.username = username;
        this.password = password;
        this.userGroups = userGroups;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userGroups;
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
}
