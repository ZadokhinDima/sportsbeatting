package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;
import com.epam.training.sportsbeatting.domain.wager.Wager;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Player extends User {

    private String name;
    private String accountNumber;
    private long balance;
    private Player.Currency currency;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "player")
    @Cascade(CascadeType.ALL)
    private Set<Wager> wagers;

    @Builder
    public Player(final Long id, final String username, final String password, final List<UserGroup> userGroups,
                  final boolean enabled, final String name, final String accountNumber, final long balance,
                  final Player.Currency currency, final LocalDate dateOfBirth, final Set<Wager> wagers) {
        super(id, username, password, userGroups, enabled);
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.dateOfBirth = dateOfBirth;
        this.wagers = wagers;
    }

    public enum Currency {
        UAH, USD, EUR;
    }

}
