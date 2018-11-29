package com.epam.training.sportsbeatting.repository.memory;

import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.repository.BetDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBetDao extends GenericInMemoryDao<Bet> implements BetDao {
}
