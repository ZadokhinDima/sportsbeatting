package com.epam.training.sportsbeatting.domain.wager;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.user.Player;

import org.springframework.data.annotation.PersistenceConstructor;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Wager extends PersistableObject {

    @ManyToOne
    private Player player;
    @ManyToOne
    private OutcomeOdd outcomeOdd;
    private Long amount;
    private Player.Currency currency;
    private LocalDateTime timestamp;
    private Boolean processed;
    private Boolean won;

    @Builder
    @PersistenceConstructor
    public Wager(final Long id, final Player player, final OutcomeOdd outcomeOdd, final Long amount,
                 final Player.Currency currency, final LocalDateTime timestamp, final Boolean processed,
                 final Boolean won) {
        super(id);
        this.player = player;
        this.outcomeOdd = outcomeOdd;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.processed = processed;
        this.won = won;
    }
}
