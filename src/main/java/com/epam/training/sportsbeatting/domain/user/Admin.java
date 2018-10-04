package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;

@Getter
@Setter
public class Admin extends User{

    private UserGroup userGroup;

    @Builder
    public Admin(final Long id, final String name, final String accountNumber, final long balance,
                 final Player.Currency currency, final LocalDate dateOfBirth, final UserGroup userGroup) {
        super(id, name, accountNumber, balance, currency, dateOfBirth);
        this.userGroup = userGroup;
    }
}
