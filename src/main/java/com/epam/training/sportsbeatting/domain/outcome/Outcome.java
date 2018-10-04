package com.epam.training.sportsbeatting.domain.outcome;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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
