package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collections;

import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;

import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
public class Player extends User {

    private String name;
    private String accountNumber;
    private long balance;
    private Player.Currency currency;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Builder
    public Player(final Long id, final String username, final String password,
                  final boolean enabled, final String name, final String accountNumber, final long balance,
                  final Currency currency, final LocalDate dateOfBirth) {
        super(id, username, password, Collections.singletonList(UserGroup.builder().authority("ROLE_PLAYER").build()),
                enabled);
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
    }

    public enum Currency {
        UAH, USD, EUR
    }

}
