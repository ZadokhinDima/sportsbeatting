package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.exception.NotEnoughBalanceException;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.service.SessionContextService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserBalanceServiceImplTest {

    @Mock
    private SessionContextService sessionContextService;
    @Mock
    private UserDao playerDao;
    @InjectMocks
    private UserBalanceServiceImpl testingInstance;

    private Player sessionPlayer;

    @Before
    public void setUp() {
        sessionPlayer = mock(Player.class);
        when(sessionContextService.getSessionUser()).thenReturn(sessionPlayer);
    }

    @Test
    public void shouldCheckUserBalanceIsLess() {
        when(sessionPlayer.getBalance()).thenReturn(50L);
        assertFalse(testingInstance.checkUserBalance(100L));
    }

    @Test
    public void shouldCheckUserBalanceIsBigger() {
        when(sessionPlayer.getBalance()).thenReturn(150L);
        assertTrue(testingInstance.checkUserBalance(100L));
    }

    @Test
    public void shouldCheckUserBalanceIsEquals() {
        when(sessionPlayer.getBalance()).thenReturn(100L);
        assertTrue(testingInstance.checkUserBalance(100L));
    }

    @Test
    public void shouldAddMoneyToUserAccount() {
        when(sessionPlayer.getBalance()).thenReturn(100L);

        testingInstance.processUserBalanceAddition(sessionPlayer, 100L);

        verify(sessionPlayer).setBalance(200L);
        verify(playerDao).save(sessionPlayer);
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void shouldThrowExceptionWhenNotEnoughMoney() throws Exception {
        when(sessionPlayer.getBalance()).thenReturn(100L);

        testingInstance.processUserBalanceSpending(sessionPlayer, 200L);
    }

    @Test
    public void shouldSignOffMoneyFromUserAccount() throws Exception {
        when(sessionPlayer.getBalance()).thenReturn(200L);

        testingInstance.processUserBalanceSpending(sessionPlayer, 200L);

        verify(sessionPlayer).setBalance(0L);
        verify(playerDao).save(sessionPlayer);
    }


}
