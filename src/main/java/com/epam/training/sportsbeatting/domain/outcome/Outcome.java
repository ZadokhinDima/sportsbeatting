package com.epam.training.sportsbeatting.domain.outcome;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Outcome {

    private Long id;
    private String value;
    private List<OutcomeOdd> outcomeOdds;

    @Builder
    public Outcome(final Long id, final String value, final List<OutcomeOdd> outcomeOdds) {
        this.id = id;
        this.value = value;
        this.outcomeOdds = outcomeOdds;
    }
}
