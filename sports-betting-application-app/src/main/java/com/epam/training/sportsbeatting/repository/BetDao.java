package com.epam.training.sportsbeatting.repository;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.repository.generic.CustomJpaRepository;

public interface BetDao extends CustomJpaRepository<Bet, Long> {
}
