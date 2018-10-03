package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.repository.PlayerDao;
import com.epam.training.sportsbeatting.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PlayerDao playerDao;

    @Override
    public void registerPlayer(Player player) {
        playerDao.save(player);
    }
}
