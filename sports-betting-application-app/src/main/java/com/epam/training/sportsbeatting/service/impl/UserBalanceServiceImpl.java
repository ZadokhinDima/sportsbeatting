package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.exception.NotEnoughBalanceException;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.UserBalanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    @Autowired
    private SessionContextService sessionContextService;
    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkUserBalance(final Long expectedAmountOfMoney) {
        final Player player = (Player) sessionContextService.getSessionUser();
        userDao.refresh(player);
        return player.getBalance() >= expectedAmountOfMoney;
    }

    @Override
    public void processUserBalanceSpending(final Player player, final Long amount) throws NotEnoughBalanceException {
        if (checkUserBalance(amount)) {
            final long newBalance = player.getBalance() - amount;
            player.setBalance(newBalance);
            userDao.save(player);
        } else {
            throw new NotEnoughBalanceException();
        }
    }

    @Override
    public void processUserBalanceAddition(final Player player, final Long amount) {
        userDao.refresh(player);
        final long newBalance = player.getBalance() + amount;
        player.setBalance(newBalance);
        userDao.save(player);
    }

}
