package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

import com.epam.training.sportsbeatting.domain.bet.Bet;

@Data
@SuperBuilder
public abstract class SportEvent {

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Bet> bets;
    private Result result;

}
