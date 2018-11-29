package com.epam.training.sportsbeatting.domain.usergroup;

import lombok.Builder;

public class Admins extends UserGroup {

    @Builder
    public Admins(final Long id) {
        super(id);
    }
}
