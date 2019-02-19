package com.epam.training.facade;

import java.util.List;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.wager.Wager;

public interface WagerFacade {

    List<Wager> getAllWagersForCurrentUser();

    void deleteWager(Long id);

    List<SportEvent> getSportEventsForWager();
}
