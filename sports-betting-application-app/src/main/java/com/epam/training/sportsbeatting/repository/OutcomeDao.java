package com.epam.training.sportsbeatting.repository;


import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.repository.generic.CustomJpaRepository;

public interface OutcomeDao extends CustomJpaRepository<Outcome, Long> {
}
