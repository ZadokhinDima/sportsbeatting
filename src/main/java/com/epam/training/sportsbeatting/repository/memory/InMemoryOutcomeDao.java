package com.epam.training.sportsbeatting.repository.memory;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.repository.OutcomeDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryOutcomeDao extends GenericInMemoryDao<Outcome> implements OutcomeDao {
}
