package com.epam.training.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class OutcomeOddData {
    private Long id;
    @NotNull
    private Long outcomeId;
    @NotNull
    private double odd;
    @NotNull
    private LocalDateTime validFrom;
    @NotNull
    private LocalDateTime validTo;

}
