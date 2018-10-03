package com.epam.training.sportsbeatting.domain.bet;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;

@Data
public class Bet {

    private Long id;
    private SportEvent sportEvent;
    private String description;
    private List<Outcome> outcomes;

    @Builder
    public Bet(final Long id, final SportEvent sportEvent, final String description, final List<Outcome> outcomes) {
        this.id = id;
        this.sportEvent = sportEvent;
        this.description = description;
        this.outcomes = outcomes;
    }
}
