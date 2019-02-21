package com.epam.training.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import javax.validation.constraints.NotNull;


@Data
@Builder
public class OutcomeData {

    private Long betId;
    private Long id;
    @NotNull
    private String value;
    private List<OutcomeOddData> outcomeOdds;

}
