package com.epam.training.sportsbeatting.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.FootballSportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.TennisSportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.BetDao;
import com.epam.training.sportsbeatting.repository.OutcomeDao;
import com.epam.training.sportsbeatting.repository.OutcomeOddDao;
import com.epam.training.sportsbeatting.repository.SportEventDao;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.repository.WagerDao;
import com.epam.training.sportsbeatting.service.InitDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitDataServiceImpl implements InitDataService {

    private static final LocalDateTime EVENTS_START_TIMESTAMP = LocalDateTime.now().plusDays(2);
    @Autowired
    private SportEventDao sportEventDao;
    @Autowired
    private BetDao betDao;
    @Autowired
    private OutcomeDao outcomeDao;
    @Autowired
    private OutcomeOddDao outcomeOddDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private WagerDao wagerDao;

    @Override
    public void initDataForGame() {
        initializeUser();
        sportEventDao.save(createFootballSportEvent());
        sportEventDao.save(createTennisSportEvent());
    }


    private void initializeUser() {
        User player = Player.builder()
                .balance(1000)
                .accountNumber("accountNumber")
                .username("user")
                .name("Dimon")
                .dateOfBirth(LocalDate.now().minusYears(18))
                .password(new BCryptPasswordEncoder().encode("user"))
                .currency(Player.Currency.UAH)
                .enabled(true)
                .build();
        userDao.save(player);
    }



    private FootballSportEvent createFootballSportEvent() {
        final FootballSportEvent footballSportEvent = FootballSportEvent.builder()
                .title("Chelsea v Liverpool")
                .startDate(EVENTS_START_TIMESTAMP)
                .endDate(EVENTS_START_TIMESTAMP.plusHours(2))
                .bets(createFootballMatchBets())
                .build();
        linkEventWithBets(footballSportEvent);
        return footballSportEvent;
    }

    private TennisSportEvent createTennisSportEvent() {
        final TennisSportEvent tennisSportEvent = TennisSportEvent.builder()
                .title("Rafael Nadal vs. Alexander Zverev, Indian Wells 4th Round")
                .startDate(EVENTS_START_TIMESTAMP)
                .endDate(EVENTS_START_TIMESTAMP.plusHours(3))
                .bets(createTennisMatchBets())
                .build();
        linkEventWithBets(tennisSportEvent);
        return tennisSportEvent;
    }

    private List<Bet> createFootballMatchBets() {
        final List<Bet> bets = Arrays.asList(createFootballWinnerBet(), createFootballGoalsBet(),
                createFootballSalahScoresBet(), createFootballHasardScoresBet());
        betDao.saveAll(bets);
        bets.forEach(this::linkBetWithOutcomes);
        return bets;
    }

    private List<Bet> createTennisMatchBets() {
        final List<Bet> bets = Arrays.asList(createTennisWinnerBet());
        betDao.saveAll(bets);
        bets.forEach(this::linkBetWithOutcomes);
        return bets;
    }

    private Bet createFootballWinnerBet() {
        List<Outcome> winnerOutcomes = new ArrayList<>();
        winnerOutcomes.add(Outcome.builder().value("Chelsea").outcomeOdds(
                Arrays.asList(createOutcomeOdd(1.8, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        winnerOutcomes.add(Outcome.builder().value("Liverpool").outcomeOdds(
                Arrays.asList(createOutcomeOdd(1.6, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        winnerOutcomes.add(Outcome.builder().value("Draw").outcomeOdds(
                Arrays.asList(createOutcomeOdd(2.3, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        winnerOutcomes.forEach(this::linkOutcomeWithOutcomeOdds);
        outcomeDao.saveAll(winnerOutcomes);
        return Bet.builder().betType(Bet.BetType.WINNER)
                .description("Winner of the match").outcomes(winnerOutcomes).build();
    }

    private Bet createFootballGoalsBet() {
        List<Outcome> goalOutcomes = new ArrayList<>();
        goalOutcomes.add(Outcome.builder().value("0").outcomeOdds(
                Arrays.asList(createOutcomeOdd(3, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        goalOutcomes.add(Outcome.builder().value("1").outcomeOdds(
                Arrays.asList(createOutcomeOdd(2.1, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        goalOutcomes.add(Outcome.builder().value("2").outcomeOdds(
                Arrays.asList(createOutcomeOdd(2.2, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        goalOutcomes.add(Outcome.builder().value(">=3").outcomeOdds(
                Arrays.asList(createOutcomeOdd(3, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        goalOutcomes.forEach(this::linkOutcomeWithOutcomeOdds);
        outcomeDao.saveAll(goalOutcomes);
        return Bet.builder().betType(Bet.BetType.GOALS_SHOT)
                .description("Amount of goals during the match").outcomes(goalOutcomes).build();
    }

    private Bet createFootballSalahScoresBet() {
        List<Outcome> scoreOuttcomes = new ArrayList<>();
        scoreOuttcomes.add(Outcome.builder().value("0").outcomeOdds(
                Arrays.asList(createOutcomeOdd(1.5, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.add(Outcome.builder().value("1").outcomeOdds(
                Arrays.asList(createOutcomeOdd(2.1, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.add(Outcome.builder().value("2").outcomeOdds(
                Arrays.asList(createOutcomeOdd(6.4, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.add(Outcome.builder().value(">=3").outcomeOdds(
                Arrays.asList(createOutcomeOdd(14.4, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.forEach(this::linkOutcomeWithOutcomeOdds);
        outcomeDao.saveAll(scoreOuttcomes);
        return Bet.builder().betType(Bet.BetType.PLAYER_SCORE)
                .description("Amount of goals Salah scores during the match").outcomes(scoreOuttcomes).build();
    }

    private Bet createFootballHasardScoresBet() {
        List<Outcome> scoreOuttcomes = new ArrayList<>();
        scoreOuttcomes.add(Outcome.builder().value("0").outcomeOdds(
                Arrays.asList(createOutcomeOdd(1.2, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.add(Outcome.builder().value("1").outcomeOdds(
                Arrays.asList(createOutcomeOdd(3.1, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.add(Outcome.builder().value("2").outcomeOdds(
                Arrays.asList(createOutcomeOdd(7.4, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.add(Outcome.builder().value(">=3").outcomeOdds(
                Arrays.asList(createOutcomeOdd(16.4, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        scoreOuttcomes.forEach(this::linkOutcomeWithOutcomeOdds);
        outcomeDao.saveAll(scoreOuttcomes);
        return Bet.builder().betType(Bet.BetType.PLAYER_SCORE)
                .description("Amount of goals Hasard scores during the match").outcomes(scoreOuttcomes).build();
    }

    private Bet createTennisWinnerBet() {
        List<Outcome> winnerOutcomes = new ArrayList<>();
        winnerOutcomes.add(Outcome.builder().value("Rafael Nadal").outcomeOdds(
                Arrays.asList(createOutcomeOdd(1.3, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        winnerOutcomes.add(Outcome.builder().value("Alexander Zverev").outcomeOdds(
                Arrays.asList(createOutcomeOdd(1.6, LocalDateTime.now(), EVENTS_START_TIMESTAMP))
        ).build());
        winnerOutcomes.forEach(this::linkOutcomeWithOutcomeOdds);
        outcomeDao.saveAll(winnerOutcomes);
        return Bet.builder().betType(Bet.BetType.WINNER)
                .description("Winner of the match").outcomes(winnerOutcomes).build();
    }


    private void linkEventWithBets(final SportEvent event) {
        event.getBets().forEach(bet -> bet.setSportEvent(event));
    }

    private void linkBetWithOutcomes(final Bet bet) {
        bet.getOutcomes().forEach(outcome -> outcome.setBet(bet));
    }

    private void linkOutcomeWithOutcomeOdds(final Outcome outcome) {
        outcome.getOutcomeOdds().forEach(outcomeOdd -> outcomeOdd.setOutcome(outcome));
    }

    private OutcomeOdd createOutcomeOdd(final double odd, final LocalDateTime validFrom, final LocalDateTime validTo) {
        final OutcomeOdd outcomeOdd = OutcomeOdd.builder().odd(odd).validFrom(validFrom).validTo(validTo).build();
        outcomeOddDao.save(outcomeOdd);
        return outcomeOdd;
    }

}
