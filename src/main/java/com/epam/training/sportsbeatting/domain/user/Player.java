package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Player extends User {

    @Builder
    public Player(final Long id, final String name, final String accountNumber, final long balance,
                  final Currency currency, final LocalDate dateOfBirth) {
        super(id, name, accountNumber, balance, currency, dateOfBirth);
    }

    public enum Currency {
        HUF, USD, EUR
    }

}
