package com.epam.training.sportsbeatting.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.epam.training.sportsbeatting.domain.sportevent.FootballSportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.repository.SportEventDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SportEventServiceImplTest {

    @Mock
    private SportEventDao sportEventDao;
    @InjectMocks
    private SportEventServiceImpl testingInstance;

    @Test
    public void shouldGetAvailableSportEvents() {
        final SportEvent pastEvent = createSportEventByDate(LocalDateTime.now().minusHours(2));
        final SportEvent futureEvent = createSportEventByDate(LocalDateTime.now().plusHours(2));
        when(sportEventDao.getAll()).thenReturn(Arrays.asList(pastEvent, futureEvent));

        final List<SportEvent> result = testingInstance.getAvailableSportEventsForWager();

        assertEquals(Collections.singletonList(futureEvent), result);
    }

    private SportEvent createSportEventByDate(LocalDateTime time) {
        return FootballSportEvent.builder().startDate(time).endDate(time.plusHours(2)).build();
    }


}