package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Player extends User {

    public enum Currency {
        HUF, USD, EUR
    }

    private String name;
    private String accountNumber;
    private long balance;
    private Currency currency;
    private LocalDate dateOfBirth;

    @Builder
    public Player(final Long id, final String name, final String accountNumber, final long balance,
                  final Currency currency, final LocalDate dateOfBirth) {
        super(id);
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
    }
}
