package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.exception.NotEnoughBalanceException;

public interface UserBalanceService {

    boolean checkUserBalance(Long expectedAmountOfMoney);

    void processUserBalanceSpending(final Player player, final Long amount) throws NotEnoughBalanceException;

    void processUserBalanceAddition(final Player player, final Long amount);
}
