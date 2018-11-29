package com.epam.training.sportsbeatting.repository.memory;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.repository.OutcomeOddDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryOutcomeOddDao extends GenericInMemoryDao<OutcomeOdd> implements OutcomeOddDao {
}
