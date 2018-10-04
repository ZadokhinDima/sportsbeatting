package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.epam.training.sportsbeatting.domain.bet.Bet;

@Setter
@Getter
@AllArgsConstructor
public abstract class SportEvent {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Bet> bets;
    private Result result;

}
