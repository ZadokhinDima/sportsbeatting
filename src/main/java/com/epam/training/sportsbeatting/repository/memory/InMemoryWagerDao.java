package com.epam.training.sportsbeatting.repository.memory;

import java.util.List;
import java.util.stream.Collectors;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.repository.WagerDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryWagerDao extends GenericInMemoryDao<Wager> implements WagerDao {

    @Override
    public List<Wager> getWagersForOutcomeOdd(final OutcomeOdd outcomeOdd) {
        return getAll().stream().filter(wager -> wager.getOutcomeOdd().equals(outcomeOdd)).collect(Collectors.toList());
    }
}
