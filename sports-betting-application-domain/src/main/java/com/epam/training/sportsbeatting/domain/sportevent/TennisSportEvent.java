package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import com.epam.training.sportsbeatting.domain.bet.Bet;

import org.springframework.data.annotation.PersistenceConstructor;

@Setter
@Getter
@Entity
public class TennisSportEvent extends SportEvent {

    @Builder
    @PersistenceConstructor
    public TennisSportEvent(final Long id, final String title, final LocalDateTime startDate,
                            final LocalDateTime endDate, final List<Bet> bets, final Result result) {
        super(id, title, startDate, endDate, bets, result);
    }
}
