package com.epam.training.sportsbeatting.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.exception.ModelNotFoundException;
import com.epam.training.sportsbeatting.repository.BetDao;
import com.epam.training.sportsbeatting.repository.OutcomeDao;
import com.epam.training.sportsbeatting.repository.OutcomeOddDao;
import com.epam.training.sportsbeatting.repository.SportEventDao;
import com.epam.training.sportsbeatting.service.SportEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportEventServiceImpl implements SportEventService {

    @Autowired
    private SportEventDao sportEventDao;
    @Autowired
    private BetDao betDao;
    @Autowired
    private OutcomeDao outcomeDao;
    @Autowired
    private OutcomeOddDao outcomeOddDao;

    @Override
    public List<SportEvent> getAvailableSportEventsForWager() {
        return sportEventDao.findAll().stream().filter(this::isSportEventAvailableForWager).collect(Collectors.toList());
    }

    @Override
    public SportEvent createSportEvent(final SportEvent sportEvent) {
        sportEventDao.save(sportEvent);
        return sportEvent;
    }

    @Override
    public Bet addBetToSportEvent(final Bet bet) {
        betDao.save(bet);
        bet.getSportEvent().getBets().add(bet);
        return bet;
    }

    @Override
    public Outcome addOutcomeToBet(final Outcome outcome) {
        outcomeDao.save(outcome);
        return outcome;
    }

    @Override
    public OutcomeOdd addOutcomeOddToOutcome(final OutcomeOdd outcomeOdd) {
        outcomeOddDao.save(outcomeOdd);
        return outcomeOdd;
    }

    @Override
    public SportEvent getSportEvent(final Long id) {
        return sportEventDao.findById(id).orElseThrow(() -> new ModelNotFoundException(SportEvent.class, id));
    }

    @Override
    public Bet getBet(final Long id) {
        return betDao.findById(id).orElseThrow(() -> new ModelNotFoundException(Bet.class, id));
    }

    @Override
    public Outcome getOutcome(final Long id) {
        return outcomeDao.findById(id).orElseThrow(() -> new ModelNotFoundException(Outcome.class, id));
    }

    private boolean isSportEventAvailableForWager(final SportEvent sportEvent) {
        return sportEvent.getStartDate().isAfter(LocalDateTime.now());
    }

}
