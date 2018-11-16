package com.epam.training.sportsbeatting.facade.impl;

import java.util.List;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.facade.SportBettingFacade;
import com.epam.training.sportsbeatting.service.InitDataService;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.SportEventResultSimulationService;
import com.epam.training.sportsbeatting.service.SportEventService;
import com.epam.training.sportsbeatting.service.UserBalanceService;
import com.epam.training.sportsbeatting.service.UserService;
import com.epam.training.sportsbeatting.service.WagerService;
import com.epam.training.sportsbeatting.ui.service.UserInteractionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportBettingFacadeImpl implements SportBettingFacade {

    @Autowired
    private UserInteractionService userInteractionService;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionContextService sessionContextService;
    @Autowired
    private InitDataService initDataService;
    @Autowired
    private SportEventService sportEventService;
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private WagerService wagerService;
    @Autowired
    private SportEventResultSimulationService simulationService;


    @Override
    public void initData() {
        initDataService.initDataForGame();
    }

    @Override
    public void registerUser() {
        final Player player = userInteractionService.getUserRegistrationInfo();
        userService.registerPlayer(player);
        sessionContextService.setSessionUser(player);
    }

    @Override
    public void performBets() {
        final List<SportEvent> events = sportEventService.getAvailableSportEventsForWager();
        userInteractionService.askForWagers(events);
    }

    @Override
    public void simulateGameResults() {
        final List<SportEvent> sportEvents = sportEventService.getAvailableSportEventsForWager();
        sportEvents.forEach(simulationService::simulateSportEventResults);
        sportEvents.forEach(userInteractionService::printResult);
        sportEvents.forEach(wagerService::processWagersForSportEvent);
        userInteractionService.printUserBalance((Player) sessionContextService.getSessionUser());
    }

}
