package com.epam.training.sportsbeatting.domain.usergroup;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

import com.epam.training.sportsbeatting.domain.PersistableObject;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class UserGroup extends PersistableObject implements GrantedAuthority {

    private String authority;

    public UserGroup(final String authority) {
        this.authority = authority;
    }

    @Builder
    @PersistenceConstructor
    public UserGroup(final Long id, final String authority) {
        super(id);
        this.authority = authority;
    }
}
