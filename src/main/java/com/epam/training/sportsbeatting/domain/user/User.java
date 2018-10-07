package com.epam.training.sportsbeatting.domain.user;

import lombok.Getter;
import lombok.Setter;

import com.epam.training.sportsbeatting.domain.PersistableObject;

@Setter
@Getter
public abstract class User extends PersistableObject {

    private String username;
    private String password;
    private boolean enabled;

    public User(final Long id, final String username, final String password, final boolean enabled) {
        super(id);
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
