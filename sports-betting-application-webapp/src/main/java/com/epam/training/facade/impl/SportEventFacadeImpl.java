package com.epam.training.facade.impl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import com.epam.training.dto.BetData;
import com.epam.training.dto.OutcomeData;
import com.epam.training.dto.OutcomeOddData;
import com.epam.training.dto.SportEventData;
import com.epam.training.facade.SportEventFacade;
import com.epam.training.sportsbeatting.domain.bet.Bet;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.sportevent.FootballSportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;
import com.epam.training.sportsbeatting.domain.sportevent.TennisSportEvent;
import com.epam.training.sportsbeatting.service.SportEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class SportEventFacadeImpl implements SportEventFacade {

    @Autowired
    private SportEventService sportEventService;

    @Override
    @Transactional
    public SportEventData createSportEvent(final SportEventData sportEventData) {
        final SportEvent sportEvent = convertFromSportEventData(sportEventData);
        sportEventService.createSportEvent(sportEvent);
        sportEventData.setId(sportEvent.getId());
        if (!CollectionUtils.isEmpty(sportEventData.getBets())) {
            sportEventData.getBets().forEach(bet -> bet.setSportEventId(sportEvent.getId()));
            sportEventData.getBets().forEach(this::addBetToSportEvent);
        }
        return sportEventData;
    }

    @Override
    @Transactional
    public BetData addBetToSportEvent(final BetData betData) {
        final Bet bet = convertFromBetData(betData);
        sportEventService.addBetToSportEvent(bet);
        betData.setId(bet.getId());
        if (!CollectionUtils.isEmpty(betData.getOutcomes())) {
            betData.getOutcomes().forEach(outcomeData -> outcomeData.setBetId(bet.getId()));
            betData.getOutcomes().forEach(this::addOutcomeToBet);
        }
        return betData;
    }

    @Override
    @Transactional
    public OutcomeData addOutcomeToBet(final OutcomeData outcomeData) {
        final Outcome outcome = convertFromOutcomeData(outcomeData);
        sportEventService.addOutcomeToBet(outcome);
        outcomeData.setId(outcome.getId());
        if (!CollectionUtils.isEmpty(outcomeData.getOutcomeOdds())) {
            outcomeData.getOutcomeOdds().forEach(outcomeOddData -> outcomeOddData.setOutcomeId(outcome.getId()));
            outcomeData.getOutcomeOdds().forEach(this::addOutcomeOddToOutcome);
        }
        return outcomeData;
    }

    @Override
    @Transactional
    public OutcomeOddData addOutcomeOddToOutcome(final OutcomeOddData outcomeOddData) {
        final OutcomeOdd outcomeOdd = convertFromOutcomeOddData(outcomeOddData);
        sportEventService.addOutcomeOddToOutcome(outcomeOdd);
        outcomeOddData.setId(outcomeOdd.getId());
        return outcomeOddData;
    }

    private SportEvent convertFromSportEventData(final SportEventData sportEventData) {
        if (sportEventData.getEventType() == SportEventData.EventType.FOOTBALL) {
            return FootballSportEvent.builder().title(sportEventData.getTitle())
                    .startDate(sportEventData.getStartTime())
                    .endDate(sportEventData.getEndTime())
                    .bets(new ArrayList<>())
                    .build();
        } else {
            return TennisSportEvent.builder().title(sportEventData.getTitle())
                    .startDate(sportEventData.getStartTime())
                    .endDate(sportEventData.getEndTime())
                    .bets(new ArrayList<>())
                    .build();
        }
    }

    private Bet convertFromBetData(final BetData betData) {
        return Bet.builder()
                .betType(betData.getType())
                .description(betData.getDescription())
                .outcomes(new ArrayList<>())
                .sportEvent(sportEventService.getSportEvent(betData.getSportEventId()))
                .build();
    }

    private Outcome convertFromOutcomeData(final OutcomeData outcomeData) {
        return Outcome.builder()
                .value(outcomeData.getValue())
                .bet(sportEventService.getBet(outcomeData.getBetId()))
                .outcomeOdds(new ArrayList<>())
                .build();
    }

    private OutcomeOdd convertFromOutcomeOddData(final OutcomeOddData outcomeOddData) {
        return OutcomeOdd.builder()
                .odd(outcomeOddData.getOdd())
                .outcome(sportEventService.getOutcome(outcomeOddData.getOutcomeId()))
                .validFrom(outcomeOddData.getValidFrom())
                .validTo(outcomeOddData.getValidTo())
                .build();
    }

}
