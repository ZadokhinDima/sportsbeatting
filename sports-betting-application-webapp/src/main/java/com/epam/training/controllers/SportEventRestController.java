package com.epam.training.controllers;

import javax.validation.Valid;

import com.epam.training.dto.BetData;
import com.epam.training.dto.OutcomeData;
import com.epam.training.dto.OutcomeOddData;
import com.epam.training.dto.SportEventData;
import com.epam.training.facade.SportEventFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class SportEventRestController {

    @Autowired
    private SportEventFacade sportEventFacade;

    @PostMapping("/event")
    public ResponseEntity<SportEventData> createSportEvent(@RequestBody @Valid SportEventData sportEventData) {
        return new ResponseEntity<>(sportEventFacade.createSportEvent(sportEventData), HttpStatus.CREATED);
    }

    @PostMapping("/bet")
    public ResponseEntity<BetData> addBetToSportEvent(@RequestBody @Valid BetData betData) {
        return new ResponseEntity<>(sportEventFacade.addBetToSportEvent(betData), HttpStatus.CREATED);
    }

    @PostMapping("/outcome")
    public ResponseEntity<OutcomeData> addOutcomeToBet(@RequestBody @Valid OutcomeData outcomeData) {
        return new ResponseEntity<>(sportEventFacade.addOutcomeToBet(outcomeData), HttpStatus.CREATED);
    }

    @PostMapping("/outcomeOdd")
    public ResponseEntity<OutcomeOddData> addOutcomeOddToOutcome(@RequestBody @Valid OutcomeOddData outcomeOddData) {
        return new ResponseEntity<>(sportEventFacade.addOutcomeOddToOutcome(outcomeOddData), HttpStatus.CREATED);
    }


}
