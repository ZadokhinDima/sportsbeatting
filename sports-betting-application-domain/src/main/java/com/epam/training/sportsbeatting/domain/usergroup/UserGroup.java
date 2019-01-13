package com.epam.training.sportsbeatting.domain.usergroup;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.epam.training.sportsbeatting.domain.PersistableObject;

import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
public class UserGroup extends PersistableObject implements GrantedAuthority {

    private String authority;

    public UserGroup(final String authority) {
        this.authority = authority;
    }

    @Builder
    public UserGroup(final Long id, final String authority) {
        super(id);
        this.authority = authority;
    }
}
