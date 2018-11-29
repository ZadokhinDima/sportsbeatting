package com.epam.training.sportsbeatting.domain.outcome;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.bet.Bet;

@Setter
@Getter
public class Outcome extends PersistableObject {

    private String value;
    private Bet bet;
    private List<OutcomeOdd> outcomeOdds;

    @Builder
    public Outcome(final Long id, final String value, final Bet bet, final List<OutcomeOdd> outcomeOdds) {
        super(id);
        this.value = value;
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;
    }

    @Override
    public String toString() {
        return bet.getDescription() + " - " + value;
    }

}
