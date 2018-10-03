package com.epam.training.sportsbeatting.ui.service.impl;

import java.time.LocalDate;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.ui.service.InputOutputService;
import com.epam.training.sportsbeatting.ui.service.UserInteractionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleUserInteractionService implements UserInteractionService {

    private static final String QUESTION_USER_NAME = "Hi, what is your name?";
    private static final String QUESTION_ACCOUNT_NUMBER = "What is your account number?";
    private static final String QUESTION_BALANCE = "What is your account number?";
    private static final String QUESTION_DATE_OF_BIRTH = "What is your account number?";

    @Autowired
    private InputOutputService ioService;

    @Override
    public Player getUserRegistrationInfo() {
        final Player.PlayerBuilder builder = Player.builder();
        askForUserName(builder);
        askForAccountNumber(builder);
        askForUserBalance(builder);
        askForDateOfBirth(builder);
        return builder.build();
    }

    @Override
    public Bet askUserForBet() {
        return null;
    }

    private void askForUserName(Player.PlayerBuilder builder) {
        ioService.print(QUESTION_USER_NAME);
        final String userName = ioService.read();
        builder.name(userName);
    }

    private void askForAccountNumber(Player.PlayerBuilder builder) {
        ioService.print(QUESTION_ACCOUNT_NUMBER);
        final String accountNumber = ioService.read();
        builder.accountNumber(accountNumber);
    }

    private void askForUserBalance(Player.PlayerBuilder builder) {
        ioService.print(QUESTION_BALANCE);
        final long userBalance = Long.parseLong(ioService.read());
        builder.balance(userBalance);
    }

    private void askForDateOfBirth(Player.PlayerBuilder builder) {
        ioService.print(QUESTION_DATE_OF_BIRTH);
        final LocalDate dateOfBirth = LocalDate.parse(ioService.read());
        builder.dateOfBirth(dateOfBirth);
    }
}
