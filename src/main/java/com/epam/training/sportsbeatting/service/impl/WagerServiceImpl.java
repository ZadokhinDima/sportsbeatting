package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.sportevent.Result;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.wager.Wager;
import com.epam.training.sportsbeatting.exception.NotEnoughBalanceException;
import com.epam.training.sportsbeatting.repository.WagerDao;
import com.epam.training.sportsbeatting.service.SessionContextService;
import com.epam.training.sportsbeatting.service.UserBalanceService;
import com.epam.training.sportsbeatting.service.WagerService;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WagerServiceImpl implements WagerService {

    @Autowired
    private WagerDao wagerDao;
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private SessionContextService sessionContextService;

    @Override
    public void placeWager(Wager wager) throws NotEnoughBalanceException {
        final Player sessionUser = (Player) sessionContextService.getSessionUser();
        userBalanceService.processUserBalanceSpending(sessionUser, wager.getAmount());
        final Wager newWager = Wager.builder()
                .amount(wager.getAmount())
                .player(sessionUser)
                .currency(sessionUser.getCurrency())
                .outcomeOdd(wager.getOutcomeOdd())
                .processed(false)
                .build();
        wagerDao.save(newWager);
    }

    @Override
    public void processWagersForSportEvent(final SportEvent sportEvent) {
        sportEvent.getBets().stream().flatMap(bet -> bet.getOutcomes().stream())
                .flatMap(outcome -> outcome.getOutcomeOdds().stream())
                .flatMap(outcomeOdd -> wagerDao.getWagersForOutcomeOdd(outcomeOdd).stream())
                .forEach(wager -> processWager(wager, sportEvent.getResult()));
    }

    @Override
    public Long calculateWagerPrize(final Wager wager) {
        if (BooleanUtils.isTrue(wager.getWon())) {
            return Math.round(wager.getAmount() * wager.getOutcomeOdd().getOdd());
        } else {
            return 0L;
        }
    }

    private void processWager(final Wager wager, final Result result) {
        final boolean isWon = result.getOutcomes().contains(wager.getOutcomeOdd().getOutcome());
        final Player sessionUser = (Player) sessionContextService.getSessionUser();
        wager.setWon(isWon);
        userBalanceService.processUserBalanceAddition(sessionUser, calculateWagerPrize(wager));
        wager.setProcessed(true);
        wagerDao.save(wager);
    }


}
