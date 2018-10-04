package com.epam.training.sportsbeatting.domain.usergroup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FootballAdmins extends UserGroup {

    @Builder
    public FootballAdmins(final Long id) {
        super(id);
    }
}
