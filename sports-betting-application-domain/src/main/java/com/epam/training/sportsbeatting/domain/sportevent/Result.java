package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.epam.training.sportsbeatting.domain.PersistableObject;
import com.epam.training.sportsbeatting.domain.outcome.Outcome;

import org.springframework.data.annotation.PersistenceConstructor;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Result extends PersistableObject {

    @OneToMany
    private List<Outcome> outcomes;
    @OneToOne
    private SportEvent sportEvent;

    @Builder
    @PersistenceConstructor
    public Result(final Long id, final List<Outcome> outcomes) {
        super(id);
        this.outcomes = outcomes;
    }

}
