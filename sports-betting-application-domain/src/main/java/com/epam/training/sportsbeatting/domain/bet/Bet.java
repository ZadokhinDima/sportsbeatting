package com.epam.training.sportsbeatting.domain.bet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;
import com.epam.training.sportsbeatting.domain.sportevent.SportEvent;

import org.springframework.data.annotation.PersistenceConstructor;


@Setter
@Getter
@Entity
public class Bet extends PersistableObject {

    @ManyToOne
    private SportEvent sportEvent;
    private String description;
    @OneToMany(mappedBy = "bet")
    private List<Outcome> outcomes;
    BetType betType;

    @Builder
    @PersistenceConstructor
    public Bet(final Long id, final SportEvent sportEvent, final String description, final List<Outcome> outcomes,
               final BetType betType) {
        super(id);
        this.sportEvent = sportEvent;
        this.description = description;
        this.outcomes = outcomes;
        this.betType = betType;
    }

    @Override
    public String toString() {
        return description;
    }

    public enum BetType {
        GOALS_SHOT, WINNER, PLAYER_SCORE
    }
}
