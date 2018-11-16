package com.epam.training.sportsbeatting.service.impl;

import java.util.Collections;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.sportevent.FootballSportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.repository.SportEventDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SportEventResultSimulationServiceImplTest {

    @Mock
    private SportEventDao sportEventDao;

    @InjectMocks
    private SportEventResultSimulationServiceImpl testingInstance;

    @Test
    public void shouldSimulateSportEventResult() {
        final Outcome outcome = Outcome.builder().build();
        final Bet bet = Bet.builder().id(1L).outcomes(Collections.singletonList(outcome)).build();
        SportEvent sportEvent = FootballSportEvent.builder().bets(Collections.singletonList(bet)).build();

        testingInstance.simulateSportEventResults(sportEvent);

        verify(sportEventDao).save(sportEvent);
        assertEquals(1, sportEvent.getResult().getOutcomes().size());
        assertEquals(outcome, sportEvent.getResult().getOutcomes().get(0));
    }

}