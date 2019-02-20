package com.epam.training.controllers;

import javax.validation.Valid;

import com.epam.training.dto.SportEventData;
import com.epam.training.facade.SportEventFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportEventRestController {

    @Autowired
    private SportEventFacade sportEventFacade;

    @PostMapping("/rest/event")
    public ResponseEntity<SportEventData> createSportEvent(@RequestBody @Valid SportEventData sportEventData) {
        return new ResponseEntity<>(sportEventFacade.createSportEvent(sportEventData), HttpStatus.CREATED);
    }


}
