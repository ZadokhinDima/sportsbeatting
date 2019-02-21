package com.epam.training.sportsbeatting.service;

import java.util.List;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;


public interface SportEventService {

    List<SportEvent> getAvailableSportEventsForWager();

    SportEvent createSportEvent(SportEvent sportEvent);

    Bet addBetToSportEvent(Bet bet);

    Outcome addOutcomeToBet(Outcome outcome);

    OutcomeOdd addOutcomeOddToOutcome(OutcomeOdd outcomeOdd);

    SportEvent getSportEvent(Long id);

    Bet getBet(Long id);

    Outcome getOutcome(Long id);

}
