package com.epam.training.sportsbeatting.domain.wager;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.user.Player;

@SuperBuilder
@Data
public class Wager {

    private Player player;
    private OutcomeOdd outcomeOdd;
    private Long amount;
    private String currency;
    private LocalDateTime timestamp;
    private Boolean processed;
    private Boolean won;

}