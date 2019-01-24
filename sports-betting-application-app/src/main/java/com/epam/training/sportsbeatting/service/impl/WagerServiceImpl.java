package com.epam.training.sportsbeatting.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.training.sportsbeatting.domain.sportevent.Result;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.user.Admin;
import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;
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

    private final static String WAGER_NOT_FOUND_MESSAGE = "Wager with id %d not found.";
    private static final String ILLEGAL_RIGHTS_TO_DELETE_WAGER = "Do not have rights to delete wager";
    private static final String NOT_PLAYER = "Current user is not player";

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
                .flatMap(outcomeOdd -> wagerDao.findByOutcomeOdd(outcomeOdd).stream())
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

    @Override
    public List<Wager> getWagersForCurrentPlayer() {
        final User sessionUser = sessionContextService.getSessionUser();
        if (sessionUser instanceof Player) {
            return wagerDao.findByPlayer((Player) sessionUser);
        } else {
            throw new IllegalArgumentException(NOT_PLAYER);
        }
    }

    @Override
    public void deleteWager(final Long id) {
        final Wager wagerToDelete = wagerDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(WAGER_NOT_FOUND_MESSAGE, id)));
        if (userAbleToDelete(wagerToDelete) && isWagerValid(wagerToDelete)) {
            userBalanceService.processUserBalanceAddition(wagerToDelete.getPlayer(), wagerToDelete.getAmount());
            wagerDao.delete(wagerToDelete);
        } else {
            throw new IllegalArgumentException(ILLEGAL_RIGHTS_TO_DELETE_WAGER);
        }
    }

    private boolean isWagerValid(final Wager wager) {
        return wager.getOutcomeOdd().getValidTo().isAfter(LocalDateTime.now());
    }

    private boolean userAbleToDelete(final Wager wager) {
        final User user = sessionContextService.getSessionUser();
        return user instanceof Admin || wager.getPlayer().getId().equals(user.getId());
    }

    private void processWager(final Wager wager, final Result result) {
        final boolean isWon = result.getOutcomes().contains(wager.getOutcomeOdd().getOutcome());
        wager.setWon(isWon);
        userBalanceService.processUserBalanceAddition(wager.getPlayer(), calculateWagerPrize(wager));
        wager.setProcessed(true);
        wagerDao.save(wager);
    }

}
