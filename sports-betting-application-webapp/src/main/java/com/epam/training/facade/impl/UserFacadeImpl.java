package com.epam.training.facade.impl;

import java.time.LocalDate;
import java.util.Optional;

import com.epam.training.dto.UserRegistrationData;
import com.epam.training.facade.UserFacade;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Player> registerUser(final UserRegistrationData userRegistrationData) {
        final Player newPlayer = convertFromRegistrationData(userRegistrationData);
        newPlayer.setEnabled(true);
        return userService.registerPlayer(newPlayer);
    }

    private Player convertFromRegistrationData(final UserRegistrationData userRegistrationData) {
        return  Player.builder()
                .username(userRegistrationData.getUsername())
                .password(passwordEncoder.encode(userRegistrationData.getPassword()))
                .name(userRegistrationData.getName())
                .dateOfBirth(LocalDate.parse(userRegistrationData.getBirthDate()))
                .balance(Long.parseLong(userRegistrationData.getBalance()))
                .accountNumber(userRegistrationData.getAccountNumber())
                .currency(userRegistrationData.getCurrency())
                .build();

    }

}
