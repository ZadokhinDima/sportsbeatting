package com.epam.training.sportsbeatting.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.sportevent.Result;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.repository.SportEventDao;
import com.epam.training.sportsbeatting.service.SportEventResultSimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportEventResultSimulationServiceImpl implements SportEventResultSimulationService {

    @Autowired
    private SportEventDao sportEventDao;

    @Override
    public void simulateSportEventResults(final SportEvent sportEvent) {
        final List<Outcome> resultOutcomes =
                sportEvent.getBets().stream().map(this::getRandomOutcomeForBet).collect(Collectors.toList());
        final Result result = Result.builder().outcomes(resultOutcomes).build();
        sportEvent.setResult(result);
        sportEventDao.save(sportEvent);
    }

    private Outcome getRandomOutcomeForBet(Bet bet) {
        final Random random = new Random();
        final List<Outcome> outcomes = bet.getOutcomes();
        return outcomes.get(random.nextInt(outcomes.size()));
    }
}
