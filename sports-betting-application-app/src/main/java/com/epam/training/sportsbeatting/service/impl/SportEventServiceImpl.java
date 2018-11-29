package com.epam.training.sportsbeatting.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.repository.SportEventDao;
import com.epam.training.sportsbeatting.service.SportEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportEventServiceImpl implements SportEventService {

    @Autowired
    private SportEventDao sportEventDao;

    @Override
    public List<SportEvent> getAvailableSportEventsForWager() {
        return sportEventDao.getAll().stream().filter(this::isSportEventAvailableForWager).collect(Collectors.toList());
    }

    private boolean isSportEventAvailableForWager(final SportEvent sportEvent) {
        return sportEvent.getStartDate().isAfter(LocalDateTime.now());
    }

}
