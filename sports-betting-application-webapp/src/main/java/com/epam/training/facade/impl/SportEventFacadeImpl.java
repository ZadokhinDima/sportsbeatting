package com.epam.training.facade.impl;

import java.util.stream.Collectors;

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
    public SportEventData createSportEvent(final SportEventData sportEventData) {
        final SportEvent sportEvent = convertFromSportEventData(sportEventData);
        sportEventService.createSportEvent(sportEvent);
        sportEventData.setId(sportEvent.getId());
        if (!CollectionUtils.isEmpty(sportEventData.getBets())) {
            sportEventData.getBets().forEach(bet -> bet.setSportEventId(sportEvent.getId()));
            sportEventData.getBets().forEach(this::addBetToSportEvent);
        }
        return getResponseFromModel(sportEvent);
    }

    @Override
    public SportEventData addBetToSportEvent(final BetData betData) {
        final Bet bet = convertFromBetData(betData);
        sportEventService.addBetToSportEvent(bet);
        betData.setId(bet.getId());
        if (!CollectionUtils.isEmpty(betData.getOutcomes())) {
            betData.getOutcomes().forEach(outcomeData -> outcomeData.setBetId(bet.getId()));
            betData.getOutcomes().forEach(this::addOutcomeToBet);
        }
        return getResponseFromModel(bet.getSportEvent());
    }

    @Override
    public SportEventData addOutcomeToBet(final OutcomeData outcomeData) {
        final Outcome outcome = convertFromOutcomeData(outcomeData);
        sportEventService.addOutcomeToBet(outcome);
        outcomeData.setId(outcome.getId());
        if (!CollectionUtils.isEmpty(outcomeData.getOutcomeOdds())) {
            outcomeData.getOutcomeOdds().forEach(outcomeOddData -> outcomeOddData.setOutcomeId(outcome.getId()));
            outcomeData.getOutcomeOdds().forEach(this::addOutcomeOddToOutcome);
        }
        return getResponseFromModel(outcome.getBet().getSportEvent());
    }

    @Override
    public SportEventData addOutcomeOddToOutcome(final OutcomeOddData outcomeOddData) {
        final OutcomeOdd outcomeOdd = convertFromOutcomeOddData(outcomeOddData);
        sportEventService.addOutcomeOddToOutcome(outcomeOdd);
        outcomeOddData.setId(outcomeOdd.getId());
        return getResponseFromModel(outcomeOdd.getOutcome().getBet().getSportEvent());
    }

    private SportEvent convertFromSportEventData(final SportEventData sportEventData) {
        if (sportEventData.getEventType() == SportEventData.EventType.FOOTBALL) {
            return FootballSportEvent.builder().title(sportEventData.getTitle())
                    .startDate(sportEventData.getStartTime())
                    .endDate(sportEventData.getEndTime())
                    .build();
        } else {
            return TennisSportEvent.builder().title(sportEventData.getTitle())
                    .startDate(sportEventData.getStartTime())
                    .endDate(sportEventData.getEndTime())
                    .build();
        }
    }

    private Bet convertFromBetData(final BetData betData) {
        return Bet.builder()
                .betType(betData.getType())
                .description(betData.getDescription())
                .sportEvent(sportEventService.getSportEvent(betData.getSportEventId()))
                .build();
    }

    private Outcome convertFromOutcomeData(final OutcomeData outcomeData) {
        return Outcome.builder()
                .value(outcomeData.getValue())
                .bet(sportEventService.getBet(outcomeData.getBetId()))
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



    private SportEventData getResponseFromModel(final SportEvent sportEvent) {
        final SportEventData.SportEventDataBuilder builder = SportEventData.builder()
                .id(sportEvent.getId())
                .endTime(sportEvent.getEndDate())
                .title(sportEvent.getTitle())
                .startTime(sportEvent.getStartDate())
                .bets(sportEvent.getBets().stream().map(this::getResponseFromModel).collect(Collectors.toList()));
        SportEventData.EventType type = sportEvent instanceof FootballSportEvent ? SportEventData.EventType.FOOTBALL :
                SportEventData.EventType.TENNIS;
        return builder.eventType(type).build();
    }

    private BetData getResponseFromModel(final Bet bet) {
        return BetData.builder()
                .id(bet.getId())
                .description(bet.getDescription())
                .type(bet.getBetType())
                .outcomes(bet.getOutcomes().stream().map(this::getResponseFromModel).collect(Collectors.toList()))
                .build();
    }

    private OutcomeData getResponseFromModel(final Outcome outcome) {
        return OutcomeData.builder()
                .id(outcome.getId())
                .value(outcome.getValue())
                .outcomeOdds(outcome.getOutcomeOdds().stream()
                        .map(this::getResponseFromModel).collect(Collectors.toList()))
                .build();
    }

    private OutcomeOddData getResponseFromModel(final OutcomeOdd outcomeOdd) {
        return OutcomeOddData.builder()
                .odd(outcomeOdd.getOdd())
                .validFrom(outcomeOdd.getValidFrom())
                .validTo(outcomeOdd.getValidTo())
                .id(outcomeOdd.getId())
                .build();
    }
}
