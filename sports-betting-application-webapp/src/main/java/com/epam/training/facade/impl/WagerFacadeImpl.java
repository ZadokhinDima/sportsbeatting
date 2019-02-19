package com.epam.training.facade.impl;

import java.util.List;

import com.epam.training.facade.WagerFacade;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.service.SportEventService;
import com.epam.training.sportsbeatting.service.WagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WagerFacadeImpl implements WagerFacade {

    @Autowired
    private WagerService wagerService;
    @Autowired
    private SportEventService sportEventService;

    @Override
    public List<Wager> getAllWagersForCurrentUser() {
        return wagerService.getWagersForCurrentPlayer();
    }

    @Override
    public List<SportEvent> getSportEventsForWager() {
        return sportEventService.getAvailableSportEventsForWager();
    }

    @Override
    public void deleteWager(Long id) {
        wagerService.deleteWager(id);
    }
}
