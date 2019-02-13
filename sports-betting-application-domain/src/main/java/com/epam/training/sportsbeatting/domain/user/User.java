package com.epam.training.sportsbeatting.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.usergroup.UserGroup;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Setter
@Getter
@Entity
@NoArgsConstructor
public abstract class User extends PersistableObject {

    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Cascade(CascadeType.ALL)
    private List<UserGroup> userGroups;
    private boolean enabled;

    public User(final Long id, final String username, final String password, final List<UserGroup> userGroups,
                final boolean enabled) {
        super(id);
        this.username = username;
        this.password = password;
        this.userGroups = userGroups;
        this.enabled = enabled;
    }

}
