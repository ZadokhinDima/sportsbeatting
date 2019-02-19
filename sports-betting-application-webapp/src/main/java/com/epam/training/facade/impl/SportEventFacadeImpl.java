package com.epam.training.facade.impl;

import com.epam.training.dto.SportEventData;
import com.epam.training.facade.SportEventFacade;
import com.epam.training.sportsbeatting.domain.sportevent.FootballSportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.TennisSportEvent;
import com.epam.training.sportsbeatting.service.SportEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SportEventFacadeImpl implements SportEventFacade {

    @Autowired
    private SportEventService sportEventService;

    @Override
    public SportEventData createSportEvent(final SportEventData sportEventData) {
        final SportEvent sportEvent = convertFromSportEventData(sportEventData);
        sportEventService.createSportEvent(sportEvent);
        sportEventData.setId(sportEvent.getId());
        return sportEventData;
    }

    private SportEvent convertFromSportEventData(final SportEventData sportEventData) {
        if (sportEventData.getEventType() == SportEventData.EventType.FOOTBALL) {
            return FootballSportEvent.builder().title(sportEventData.getTitle())
                    .startDate(sportEventData.getStartTime())
                    .endDate(sportEventData.getEndTime())
                    .build();
        } else {
            return TennisSportEvent.builder().title(sportEventData.getTitle())
                    .startDate(sportEventData.getStartTime())
                    .endDate(sportEventData.getEndTime())
                    .build();
        }
    }
}
