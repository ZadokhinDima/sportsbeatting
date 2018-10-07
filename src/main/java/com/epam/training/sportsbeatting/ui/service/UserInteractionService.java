package com.epam.training.sportsbeatting.ui.service;

import java.util.List;
import java.util.Optional;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;

public interface UserInteractionService {

    Player getUserRegistrationInfo();

    Optional<OutcomeOdd> askUserForOutcomeOdd(final List<SportEvent> sportEvents);

    Optional<Long> getAmountOfMoneyForBet();

    void printUserBalance(Player user);

    void printResult(SportEvent sportEvent);
}
