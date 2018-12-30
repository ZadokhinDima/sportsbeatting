package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.repository.PlayerDao;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private UserDao userDao;

    @Override
    public void registerPlayer(Player player) {
        playerDao.save(player);
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) {
        return userDao.getUserByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + userName + "not found."));
    }
}
