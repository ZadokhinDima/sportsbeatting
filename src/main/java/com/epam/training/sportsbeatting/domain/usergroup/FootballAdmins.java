package com.epam.training.sportsbeatting.domain.usergroup;

import lombok.Builder;
import lombok.Data;

@Data
public class FootballAdmins extends UserGroup {

    @Builder
    public FootballAdmins(final Long id) {
        super(id);
    }
}
