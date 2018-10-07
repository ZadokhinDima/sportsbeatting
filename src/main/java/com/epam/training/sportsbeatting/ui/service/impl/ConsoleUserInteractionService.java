package com.epam.training.sportsbeatting.ui.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.ui.service.InputOutputService;
import com.epam.training.sportsbeatting.ui.service.UserInteractionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleUserInteractionService implements UserInteractionService {

    private static final String QUESTION_USER_NAME = "Hi, what is your name?";
    private static final String QUESTION_ACCOUNT_NUMBER = "What is your account number?";
    private static final String QUESTION_BALANCE = "How much money do you have (more than 0)?";
    private static final String QUESTION_CURRENCY = "What is your currency? (UAH, EUR or USD)";
    private static final String QUESTION_DATE_OF_BIRTH = " When were you born? eg.:1990-02-03";

    private static final String CHOOSE_SPORT_EVENT =
            "Please choose a sport event to bet on! (choose a number or press q for quit)";

    private static final String CHOOSE_BET =
            "Please choose a bet! (choose a number or press q for quit)";

    private static final String CHOOSE_OUTCOME =
            "Please choose an outcome! (choose a number or press q for quit)";

    private static final String INPUT_AMOUNT_OF_MONEY = "How much do you want to bet on it? (q for quit)";

    @Autowired
    private InputOutputService ioService;

    @Override
    public Player getUserRegistrationInfo() {
        final Player.PlayerBuilder builder = Player.builder();
        askForUserName(builder);
        askForAccountNumber(builder);
        askForUserBalance(builder);
        askForUserCurrency(builder);
        askForDateOfBirth(builder);
        return builder.build();
    }

    @Override
    public Optional<OutcomeOdd> askUserForOutcomeOdd(final List<SportEvent> sportEvents) {
        final Optional<SportEvent> sportEvent = chooseFromList(CHOOSE_SPORT_EVENT, sportEvents);
        if (!sportEvent.isPresent()) {
            return Optional.empty();
        }
        final Optional<Bet> bet = chooseFromList(CHOOSE_BET, sportEvent.get().getBets());
        if (!bet.isPresent()) {
            return Optional.empty();
        }
        return chooseFromList(CHOOSE_OUTCOME, getAvailableOutcomeOddsForBet(bet.get()));
    }


    @Override
    public Optional<Long> getAmountOfMoneyForBet() {
        ioService.print(INPUT_AMOUNT_OF_MONEY);
        final String input = ioService.read();
        if (input.equals("q")) {
            return Optional.empty();
        } else {
            return Optional.of(Long.valueOf(input));
        }
    }

    @Override
    public void printUserBalance(final Player user) {
        ioService.print("Your new balance is " + user.getBalance() + " " + user.getCurrency());
    }

    @Override
    public void printResult(final SportEvent sportEvent) {
        ioService.print("Result for event " + sportEvent.toString());
        final List<Bet> bets = sportEvent.getBets();
        sportEvent.getResult().getOutcomes().stream().map(Outcome::toString).forEach(ioService::print);
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

    private void askForUserCurrency(Player.PlayerBuilder builder) {
        ioService.print(QUESTION_CURRENCY);
        final Player.Currency userCurrency = Player.Currency.valueOf(ioService.read());
        builder.currency(userCurrency);
    }

    private void askForDateOfBirth(Player.PlayerBuilder builder) {
        ioService.print(QUESTION_DATE_OF_BIRTH);
        final LocalDate dateOfBirth = LocalDate.parse(ioService.read());
        builder.dateOfBirth(dateOfBirth);
    }

    private <T> Optional<T> chooseFromList(final String question, final List<T> variants) {
        ioService.print(question);
        for (int i = 0; i < variants.size(); i++) {
            ioService.print((i + 1) + ": " + variants.get(i).toString());
        }
        final String input = ioService.read();
        if (input.equals("q")) {
            return Optional.empty();
        } else {
            return Optional.of(variants.get(Integer.parseInt(input) - 1));
        }
    }

    private List<OutcomeOdd> getAvailableOutcomeOddsForBet(final Bet bet) {
        return bet.getOutcomes().stream().flatMap(outcome -> outcome.getOutcomeOdds().stream())
                .filter(this::isOutcomeOddAvailable).collect(Collectors.toList());
    }

    private boolean isOutcomeOddAvailable(OutcomeOdd outcomeOdd) {
        return LocalDateTime.now().isAfter(outcomeOdd.getValidFrom())
                && LocalDateTime.now().isBefore(outcomeOdd.getValidTo());
    }
}
