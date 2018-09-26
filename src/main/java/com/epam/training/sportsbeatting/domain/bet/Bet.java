package com.epam.training.sportsbeatting.domain.bet;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;

@SuperBuilder
@Data
public class Bet {

    private SportEvent sportEvent;
    private List<Outcome> outcomes;

}
