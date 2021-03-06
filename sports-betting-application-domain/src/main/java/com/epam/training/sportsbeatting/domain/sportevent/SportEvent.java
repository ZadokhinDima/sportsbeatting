package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.bet.Bet;

@Setter
@Getter
@Entity
@NoArgsConstructor
public abstract class SportEvent extends PersistableObject {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToMany(mappedBy = "sportEvent")
    private List<Bet> bets;
    @OneToOne
    private Result result;

    public SportEvent(final Long id, final String title, final LocalDateTime startDate, final LocalDateTime endDate,
                      final List<Bet> bets, final Result result) {
        super(id);
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bets = bets;
        this.result = result;
    }

    @Override
    public String toString() {
        return title + " at " + startDate;
    }
}
