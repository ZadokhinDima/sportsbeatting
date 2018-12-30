package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;


@Getter
@Setter
public class Admin extends User {

    @Builder
    public Admin(final Long id, final String username, final String password, final boolean enabled) {
        super(id, username, password, Collections.
                singletonList(UserGroup.builder().authority("admin").build()), enabled);
    }
}
