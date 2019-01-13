package com.epam.training.sportsbeatting.service;


import java.util.Optional;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Optional<Player> registerPlayer(Player player);

    void updateUserInfo(User user);

    User getUserById(Long id);

}
