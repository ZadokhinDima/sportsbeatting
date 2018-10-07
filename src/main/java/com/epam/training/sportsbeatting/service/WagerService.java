package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.wager.Wager;

public interface WagerService {

    void placeWager(OutcomeOdd outcomeOdd, Long amountOfMoney);

    void processWagersForSportEvent(SportEvent sportEvent);

    Long calculateWagerPrize(Wager wager);

}
