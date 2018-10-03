package com.epam.training.sportsbeatting.domain.usergroup;

import lombok.Builder;
import lombok.Data;

@Data
public class TennisAdmins extends UserGroup {

    @Builder
    public TennisAdmins(final Long id) {
        super(id);
    }
}
