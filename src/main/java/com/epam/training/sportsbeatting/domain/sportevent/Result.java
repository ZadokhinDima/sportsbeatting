package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;

@Data
public class Result {

    private Long id;
    private List<Outcome> outcomes;

    @Builder
    public Result(final Long id, final List<Outcome> outcomes) {
        this.id = id;
        this.outcomes = outcomes;
    }

}
