package com.epam.training.sportsbeatting.domain.outcome;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OutcomeOdd {

    private Long id;
    private Outcome outcome;
    private double odd;
    private LocalDate validFrom;
    private LocalDate validTo;

    @Builder
    public OutcomeOdd(final Long id, final Outcome outcome, final double odd, final LocalDate validFrom,
                      final LocalDate validTo) {
        this.id = id;
        this.outcome = outcome;
        this.odd = odd;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

}
