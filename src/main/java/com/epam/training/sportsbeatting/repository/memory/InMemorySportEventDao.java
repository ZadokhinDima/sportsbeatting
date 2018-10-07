package com.epam.training.sportsbeatting.repository.memory;

import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.repository.SportEventDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemorySportEventDao extends GenericInMemoryDao<SportEvent> implements SportEventDao {

}
