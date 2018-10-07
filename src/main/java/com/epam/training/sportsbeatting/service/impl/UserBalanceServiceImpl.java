package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.repository.PlayerDao;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.UserBalanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    @Autowired
    private SessionContextService sessionContextService;
    @Autowired
    private PlayerDao playerDao;

    @Override
    public boolean checkUserBalance(final Long expectedAmountOfMoney) {
        final Player player = (Player) sessionContextService.getSessionUser();
        playerDao.refresh(player);
        return player.getBalance() >= expectedAmountOfMoney;
    }

    @Override
    public void processUserBalanceUpdate(final Player player, final Long amount, final UpdateBalanceType type) {
        playerDao.refresh(player);
        long playerBalance = player.getBalance();
        if (type == UpdateBalanceType.ADDITION) {
            playerBalance += amount;
        } else {
            if (amount > playerBalance) {
                throw new RuntimeException("Not enough money.");
            }
            playerBalance -= amount;
        }
        player.setBalance(playerBalance);
        playerDao.save(player);
    }
}
