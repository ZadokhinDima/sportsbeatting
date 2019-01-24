package com.epam.training.sportsbeatting.domain.outcome;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.epam.training.sportsbeatting.domain.PersistableObject;

import org.springframework.data.annotation.PersistenceConstructor;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class OutcomeOdd extends PersistableObject {

    @ManyToOne
    private Outcome outcome;
    private double odd;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    @Builder
    @PersistenceConstructor
    public OutcomeOdd(final Long id, final Outcome outcome, final double odd, final LocalDateTime validFrom,
                      final LocalDateTime validTo) {
        super(id);
        this.outcome = outcome;
        this.odd = odd;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public String toString() {
        return outcome.getValue() + ". The odd on this is " + odd;
    }
}
