package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;


@Getter
@Setter
public class Admin extends User {

    private UserGroup userGroup;

    @Builder

    public Admin(final Long id, final String username, final String password, final boolean enabled,
                 final UserGroup userGroup) {
        super(id, username, password, enabled);
        this.userGroup = userGroup;
    }
}
