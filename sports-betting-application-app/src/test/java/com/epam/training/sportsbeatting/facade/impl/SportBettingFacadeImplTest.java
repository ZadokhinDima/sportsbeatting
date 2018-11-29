package com.epam.training.sportsbeatting.facade.impl;

import java.util.Collections;
import java.util.List;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.service.InitDataService;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.SportEventResultSimulationService;
import com.epam.training.sportsbeatting.service.SportEventService;
import com.epam.training.sportsbeatting.service.UserBalanceService;
import com.epam.training.sportsbeatting.service.UserService;
import com.epam.training.sportsbeatting.service.WagerService;
import com.epam.training.sportsbeatting.ui.service.UserInteractionService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SportBettingFacadeImplTest {

    @Mock
    private UserInteractionService userInteractionService;
    @Mock
    private UserService userService;
    @Mock
    private SessionContextService sessionContextService;
    @Mock
    private InitDataService initDataService;
    @Mock
    private SportEventService sportEventService;
    @Mock
    private UserBalanceService userBalanceService;
    @Mock
    private WagerService wagerService;
    @Mock
    private SportEventResultSimulationService simulationService;

    @InjectMocks
    private SportBettingFacadeImpl testingInstance;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldInitData() {
        testingInstance.initData();

        verify(initDataService).initDataForGame();
    }

    @Test
    public void shouldRegisterUser() {
        final Player player = mock(Player.class);
        when(userInteractionService.getUserRegistrationInfo()).thenReturn(player);

        testingInstance.registerUser();

        verify(userService).registerPlayer(player);
        verify(sessionContextService).setSessionUser(player);
    }

    @Test
    public void shouldPerformBets() {
        final List<SportEvent> sportEvents = Collections.singletonList(mock(SportEvent.class));
        when(sportEventService.getAvailableSportEventsForWager()).thenReturn(sportEvents);

        testingInstance.performBets();

        verify(sportEventService).getAvailableSportEventsForWager();
        verify(userInteractionService).askForWagers(sportEvents);
    }

    @Test
    public void shouldSimulateGameResults() {
        final SportEvent event = mock(SportEvent.class);
        when(sportEventService.getAvailableSportEventsForWager()).thenReturn(Collections.singletonList(event));

        testingInstance.simulateGameResults();

        verify(sportEventService).getAvailableSportEventsForWager();
        verify(simulationService).simulateSportEventResults(event);
        verify(userInteractionService).printResult(event);
        verify(wagerService).processWagersForSportEvent(event);
    }

}
