package com.epam.training.sportsbeatting.repository.memory;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.repository.PlayerDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPlayerDao extends GenericInMemoryDao<Player> implements PlayerDao {

}
