package com.epam.training.sportsbeatting.service.impl;

import java.util.Optional;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<Player> registerPlayer(Player player) {
        final Optional<User> userByName = userDao.getUserByName(player.getUsername());
        if (userByName.isPresent()) {
            return Optional.empty();
        } else {
            userDao.save(player);
            return Optional.of(player);
        }

    }

    @Override
    public UserDetails loadUserByUsername(final String userName) {
        return userDao.getUserByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + userName + "not found."));
    }

    @Override
    public void updateUserInfo(final User user) {
        userDao.save(user);
    }

    @Override
    public User getUserById(final Long id) {
        return userDao.get(id);
    }
}
