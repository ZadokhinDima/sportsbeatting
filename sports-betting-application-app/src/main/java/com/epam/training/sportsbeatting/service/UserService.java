package com.epam.training.sportsbeatting.service;


import com.epam.training.sportsbeatting.domain.user.Player;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void registerPlayer(Player player);

}
