package com.epam.training.sportsbeatting.domain.bet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;


@Setter
@Getter
public class Bet extends PersistableObject {

    private SportEvent sportEvent;
    private String description;
    private List<Outcome> outcomes;
    BetType betType;

    @Builder
    public Bet(final Long id, final SportEvent sportEvent, final String description, final List<Outcome> outcomes,
               final BetType betType) {
        super(id);
        this.sportEvent = sportEvent;
        this.description = description;
        this.outcomes = outcomes;
        this.betType = betType;
    }

    @Override
    public String toString() {
        return description;
    }

    public enum BetType {
        GOALS_SHOT, WINNER, PLAYER_SCORE
    }
}
