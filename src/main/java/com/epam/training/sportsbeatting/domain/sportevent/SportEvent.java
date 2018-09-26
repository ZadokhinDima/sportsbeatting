package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

import com.epam.training.sportsbeatting.domain.bet.Bet;

@Data
@SuperBuilder
public class SportEvent {

    private List<Bet> bets;
    private Result result;

}
