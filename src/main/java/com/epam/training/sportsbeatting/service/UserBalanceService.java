package com.epam.training.sportsbeatting.service;

import com.epam.training.sportsbeatting.domain.user.Player;

public interface UserBalanceService {

    boolean checkUserBalance(Long expectedAmountOfMoney);

    void processUserBalanceUpdate(final Player player, final Long amount, final UpdateBalanceType type);

    enum UpdateBalanceType {
        ADDITION, SPENDING
    }

}
