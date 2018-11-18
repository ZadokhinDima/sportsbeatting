package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.repository.PlayerDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private PlayerDao playerDao;
    @InjectMocks
    private UserServiceImpl testingInstance;

    @Test
    public void shouldSaveNewUser() {
        Player player = mock(Player.class);
        testingInstance.registerPlayer(player);

        verify(playerDao).save(player);
    }

}