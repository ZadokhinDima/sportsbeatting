package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.repository.generic.CustomJpaRepository;

public interface SportEventDao extends CustomJpaRepository<SportEvent, Long> {
}
