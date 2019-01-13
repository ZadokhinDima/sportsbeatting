package com.epam.training.facade.impl;

import java.time.LocalDate;
import java.util.Optional;

import com.epam.training.dto.UserData;
import com.epam.training.dto.UserUpdateData;
import com.epam.training.facade.UserFacade;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.UserService;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @Autowired
    private SessionContextService sessionContextService;

    @Override
    public Optional<Player> registerUser(final UserData userData) {
        final Player newPlayer = convertFromRegistrationData(userData);
        newPlayer.setEnabled(true);
        return userService.registerPlayer(newPlayer);
    }

    @Override
    public Player getCurrentPlayerInfo() {
        return (Player) sessionContextService.getSessionUser();
    }

    @Override
    public void updateUserInfo(final UserUpdateData userData) {
        final User updatedUser = userService.getUserById(userData.getId());
        if (updatedUser instanceof Player) {
            final Player player = (Player) updatedUser;
            player.setName(userData.getName());
            player.setAccountNumber(userData.getAccountNumber());
            if (Strings.isNotBlank(userData.getNewPassword())) {
                player.setPassword(passwordEncoder.encode(userData.getNewPassword()));
            }
        }
        userService.updateUserInfo(updatedUser);
    }

    private Player convertFromRegistrationData(final UserData userData) {
        return  Player.builder()
                .username(userData.getUsername())
                .password(passwordEncoder.encode(userData.getPassword()))
                .name(userData.getName())
                .dateOfBirth(LocalDate.parse(userData.getBirthDate()))
                .balance(Long.parseLong(userData.getBalance()))
                .accountNumber(userData.getAccountNumber())
                .currency(userData.getCurrency())
                .build();

    }

}
