package com.epam.training.sportsbeatting.domain.outcome;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
public class OutcomeOdd {
    private Outcome outcome;
    private double odd;
    private LocalDate validFrom;
    private LocalDate validTo;
}
