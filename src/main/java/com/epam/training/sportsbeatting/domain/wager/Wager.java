package com.epam.training.sportsbeatting.domain.wager;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.epam.training.sportsbeatting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbeatting.domain.user.Player;

@Setter
@Getter
public class Wager {

    private Long id;
    private Player player;
    private OutcomeOdd outcomeOdd;
    private Long amount;
    private String currency;
    private LocalDateTime timestamp;
    private Boolean processed;
    private Boolean won;

    @Builder
    public Wager(final Long id, final Player player, final OutcomeOdd outcomeOdd, final Long amount,
                 final String currency, final LocalDateTime timestamp, final Boolean processed, final Boolean won) {
        this.id = id;
        this.player = player;
        this.outcomeOdd = outcomeOdd;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.processed = processed;
        this.won = won;
    }
}
