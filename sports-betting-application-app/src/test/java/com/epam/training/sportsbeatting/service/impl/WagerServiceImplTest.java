package com.epam.training.sportsbeatting.service.impl;

import java.util.Arrays;
import java.util.Collections;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.Result;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.exception.NotEnoughBalanceException;
import com.epam.training.sportsbeatting.repository.WagerDao;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.UserBalanceService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WagerServiceImplTest {


    @Mock
    private WagerDao wagerDao;
    @Mock
    private UserBalanceService userBalanceService;
    @Mock
    private SessionContextService sessionContextService;
    @InjectMocks
    private WagerServiceImpl testingInstance;

    private Player player;

    @Before
    public void setUp() {
        player = mock(Player.class);
        when(player.getCurrency()).thenReturn(Player.Currency.UAH);
        when(sessionContextService.getSessionUser()).thenReturn(player);
    }

    @Test
    public void shouldPlaceWager() throws NotEnoughBalanceException {
        OutcomeOdd outcomeOdd = OutcomeOdd.builder().build();
        Wager wager = Wager.builder().amount(100L).outcomeOdd(outcomeOdd).build();
        ArgumentCaptor<Wager> captor = ArgumentCaptor.forClass(Wager.class);

        testingInstance.placeWager(wager);

        verify(userBalanceService).processUserBalanceSpending(player, 100L);
        verify(wagerDao).save(captor.capture());
        final Wager actual = captor.getValue();
        assertEquals(player, actual.getPlayer());
        assertEquals(new Long(100), actual.getAmount());
        assertEquals(Player.Currency.UAH, actual.getCurrency());
        assertEquals(outcomeOdd, actual.getOutcomeOdd());
        assertFalse(actual.getProcessed());
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void shouldThrowExceptionWhenNotEnoughMoney() throws NotEnoughBalanceException {
        OutcomeOdd outcomeOdd = OutcomeOdd.builder().build();
        Wager wager = Wager.builder().amount(100L).outcomeOdd(outcomeOdd).build();
        doThrow(new NotEnoughBalanceException()).when(userBalanceService).processUserBalanceSpending(any(), any());
        testingInstance.placeWager(wager);

    }

    @Test
    public void shouldMultiplyOddAndWagerAmount() {
        final OutcomeOdd outcomeOdd = OutcomeOdd.builder().odd(2).build();
        Wager wager = Wager.builder().won(true).outcomeOdd(outcomeOdd).amount(20L).build();

        assertEquals(new Long(40), testingInstance.calculateWagerPrize(wager));
    }

    @Test
    public void shouldReturnZeroOnLostWager() {
        final OutcomeOdd outcomeOdd = OutcomeOdd.builder().odd(2).build();
        Wager wager = Wager.builder().won(false).outcomeOdd(outcomeOdd).amount(20L).build();

        assertEquals(new Long(0), testingInstance.calculateWagerPrize(wager));
    }

    @Test
    public void shouldProcessWagersForSportEvent() {
        SportEvent sportEvent = mock(SportEvent.class);
        Bet bet1 = mock(Bet.class);
        Bet bet2 = mock(Bet.class);
        when(sportEvent.getBets()).thenReturn(Arrays.asList(bet1, bet2));
        Outcome outcome1 = mock(Outcome.class);
        Outcome outcome2 = mock(Outcome.class);
        when(bet1.getOutcomes()).thenReturn(Collections.singletonList(outcome1));
        when(bet2.getOutcomes()).thenReturn(Collections.singletonList(outcome2));
        OutcomeOdd outcomeOdd1 = mock(OutcomeOdd.class);
        OutcomeOdd outcomeOdd2 = mock(OutcomeOdd.class);
        when(outcome1.getOutcomeOdds()).thenReturn(Collections.singletonList(outcomeOdd1));
        when(outcomeOdd1.getOutcome()).thenReturn(outcome1);
        when(outcome2.getOutcomeOdds()).thenReturn(Collections.singletonList(outcomeOdd2));
        when(outcomeOdd2.getOutcome()).thenReturn(outcome2);
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Wager wager1 = Wager.builder().outcomeOdd(outcomeOdd1).player(player1).amount(100L).build();
        Wager wager2 = Wager.builder().outcomeOdd(outcomeOdd2).player(player2).amount(100L).build();
        when(wagerDao.getWagersForOutcomeOdd(outcomeOdd1)).thenReturn(Collections.singletonList(wager1));
        when(wagerDao.getWagersForOutcomeOdd(outcomeOdd2)).thenReturn(Collections.singletonList(wager2));
        when(outcomeOdd1.getOdd()).thenReturn(3.);


        when(sportEvent.getResult()).thenReturn(Result.builder().outcomes(Collections.singletonList(outcome1)).build());

        testingInstance.processWagersForSportEvent(sportEvent);

        assertTrue(wager1.getWon());
        assertFalse(wager2.getWon());
        assertTrue(wager1.getProcessed());
        assertTrue(wager2.getProcessed());
        verify(userBalanceService).processUserBalanceAddition(player1, 300L);
        verify(userBalanceService).processUserBalanceAddition(player2, 0L);
        verify(wagerDao, times(2)).save(any());

    }

}