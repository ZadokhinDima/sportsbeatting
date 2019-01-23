package com.epam.training.sportsbeatting.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;

import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;

import org.springframework.data.annotation.PersistenceConstructor;


@Getter
@Setter
@Entity
public class Admin extends User {

    @Builder
    @PersistenceConstructor
    public Admin(final Long id, final String username, final String password, final List<UserGroup> userGroups,
                 final boolean enabled) {
        super(id, username, password, userGroups, enabled);
    }
}
