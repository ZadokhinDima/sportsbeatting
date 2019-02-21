package com.epam.training.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.epam.training.sportsbeatting.domain.bet.Bet;

@Data
@Builder
public class BetData {

    private Long sportEventId;
    private Long id;
    @NotNull
    private String description;
    private List<OutcomeData> outcomes;
    @NotNull
    private Bet.BetType type;

}
