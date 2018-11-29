package com.epam.training.sportsbeatting.repository;

import java.util.List;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.wager.Wager;


public interface WagerDao extends GenericDao<Wager> {

    List<Wager> getWagersForOutcomeOdd(OutcomeOdd outcomeOdd);

}
