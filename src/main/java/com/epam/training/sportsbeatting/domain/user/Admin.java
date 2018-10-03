package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;

import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;

public class Admin extends User{

    private UserGroup userGroup;

    @Builder
    public Admin(final Long id, final UserGroup userGroup) {
        super(id);
        this.userGroup = userGroup;
    }
}
