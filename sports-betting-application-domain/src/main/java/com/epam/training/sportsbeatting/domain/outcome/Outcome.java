package com.epam.training.sportsbeatting.domain.outcome;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.bet.Bet;

import org.springframework.data.annotation.PersistenceConstructor;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Outcome extends PersistableObject {

    private String value;
    @ManyToOne
    private Bet bet;
    @OneToMany(mappedBy = "outcome")
    private List<OutcomeOdd> outcomeOdds;

    @Builder
    @PersistenceConstructor
    public Outcome(final Long id, final String value, final Bet bet, final List<OutcomeOdd> outcomeOdds) {
        super(id);
        this.value = value;
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;
    }

    @Override
    public String toString() {
        return bet.getDescription() + " - " + value;
    }

}
