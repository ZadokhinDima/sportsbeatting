package com.epam.training.sportsbeatting.facade.impl;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.facade.SportBettingFacade;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.UserService;
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

    @Override
    public void initData() {

    }

    @Override
    public void registerUser() {
        final Player player = userInteractionService.getUserRegistrationInfo();
        userService.registerPlayer(player);
        sessionContextService.setSessionUser(player);
    }

    @Override
    public void performBets() {

    }

    @Override
    public void calculateResults() {

    }
}
