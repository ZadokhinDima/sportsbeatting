package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Player extends User {

    private String name;
    private String accountNumber;
    private long balance;
    private Player.Currency currency;
    private LocalDate dateOfBirth;

    @Builder
    public Player(final Long id, final String username, final String password, final boolean enabled,
                  final String name, final String accountNumber, final long balance, final Currency currency,
                  final LocalDate dateOfBirth) {
        super(id, username, password, enabled);
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
    }

    public enum Currency {
        UAH, USD, EUR;
    }

}
