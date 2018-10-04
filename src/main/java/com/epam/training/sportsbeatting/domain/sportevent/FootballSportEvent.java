package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.epam.training.sportsbeatting.domain.bet.Bet;

@Setter
@Getter
public class FootballSportEvent extends SportEvent {

    @Builder
    public FootballSportEvent(final Long id, final String title, final LocalDate startDate, final LocalDate endDate,
                              final List<Bet> bets, final Result result) {
        super(id, title, startDate, endDate, bets, result);
    }

}
