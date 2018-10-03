package com.epam.training.sportsbeatting.ui.service;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.user.Player;

public interface UserInteractionService {

    Player getUserRegistrationInfo();

    Bet askUserForBet();

}
