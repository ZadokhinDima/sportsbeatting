package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.repository.BetDao;
import com.epam.training.sportsbeatting.repository.OutcomeDao;
import com.epam.training.sportsbeatting.repository.OutcomeOddDao;
import com.epam.training.sportsbeatting.repository.SportEventDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InitDataServiceImplTest {

    @Mock
    private SportEventDao sportEventDao;
    @Mock
    private BetDao betDao;
    @Mock
    private OutcomeDao outcomeDao;
    @Mock
    private OutcomeOddDao outcomeOddDao;
    @InjectMocks
    private final InitDataServiceImpl testingInstance = new InitDataServiceImpl();


    @Test
    public void shouldSaveSomeData() {
        testingInstance.initDataForGame();

        verify(sportEventDao, times(2)).save(any());
        verify(betDao, times(2)).saveAll(any());
        verify(outcomeDao, times(5)).saveAll(any());
        verify(outcomeOddDao, times(17)).save(any());
    }


}