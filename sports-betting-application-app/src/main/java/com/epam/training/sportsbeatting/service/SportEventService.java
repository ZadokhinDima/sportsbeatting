package com.epam.training.sportsbeatting.service;

import java.util.List;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;


public interface SportEventService {

    List<SportEvent> getAvailableSportEventsForWager();

    SportEvent createSportEvent(SportEvent sportEvent);

}
