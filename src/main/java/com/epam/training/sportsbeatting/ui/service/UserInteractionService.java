package com.epam.training.sportsbeatting.ui.service;

import java.util.List;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.wager.Wager;

public interface UserInteractionService {

    Player getUserRegistrationInfo();

    List<Wager> askForWagers(List<SportEvent> sportEvents);

    void printUserBalance(Player user);

    void printResult(SportEvent sportEvent);
}
