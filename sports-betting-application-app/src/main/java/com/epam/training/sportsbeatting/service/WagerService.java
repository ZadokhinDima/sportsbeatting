package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.exception.NotEnoughBalanceException;

public interface WagerService {

    void placeWager(Wager wager) throws NotEnoughBalanceException;

    void processWagersForSportEvent(SportEvent sportEvent);

    Long calculateWagerPrize(Wager wager);

}
