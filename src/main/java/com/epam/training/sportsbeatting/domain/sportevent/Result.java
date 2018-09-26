package com.epam.training.sportsbeatting.domain.sportevent;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

import com.epam.training.sportsbeatting.domain.outcome.Outcome;

@Data
@SuperBuilder
public class Result {
    private List<Outcome> outcomes;
}
