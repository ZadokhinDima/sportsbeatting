package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;

@Setter
@Getter
public class Result extends PersistableObject {

    private List<Outcome> outcomes;

    @Builder
    public Result(final Long id, final List<Outcome> outcomes) {
        super(id);
        this.outcomes = outcomes;
    }

}
