package com.epam.training.sportsbeatting.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public abstract class User {

    private Long id;
    private String name;
    private String accountNumber;
    private long balance;
    private Player.Currency currency;
    private LocalDate dateOfBirth;

}
