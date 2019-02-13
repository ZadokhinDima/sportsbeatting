package com.epam.training.sportsbeatting.service.impl;

import java.util.Optional;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.security.ApplicationUserDetails;
import com.epam.training.sportsbeatting.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with id %d not found.";
    private static final String USERNAME_NOT_FOUND_MESSAGE = "User with name %s not found.";

    @Autowired
    private UserDao userDao;

    @Override
    public Optional<Player> registerPlayer(Player player) {
        final Optional<User> userByName = userDao.findByUsername(player.getUsername());
        if (userByName.isPresent()) {
            return Optional.empty();
        } else {
            userDao.save(player);
            return Optional.of(player);
        }

    }

    @Override
    public UserDetails loadUserByUsername(final String userName) {
        final User user = userDao.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND_MESSAGE, userName)));
        return new ApplicationUserDetails(user);
    }

    @Override
    public void updateUserInfo(final User user) {
        userDao.save(user);
    }

    @Override
    public User getUserById(final Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(USER_NOT_FOUND_MESSAGE, id)));
    }
}
