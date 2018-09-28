package com.epam.training.sportsbeatting.domain.user;

import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
public class Player extends User {

    public enum Currency {
        HUF, USD, EUR
    }

    private String name;
    private String accountNumber;
    private long balance;
    private Currency currency;
    private LocalDate dateOfBirth;

}
