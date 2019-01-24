package com.epam.training.sportsbeatting.repository;

import java.util.List;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.repository.generic.CustomJpaRepository;


public interface WagerDao extends CustomJpaRepository<Wager, Long> {

    List<Wager> findByOutcomeOdd(OutcomeOdd outcomeOdd);

    List<Wager> findByPlayer(Player player);

}
