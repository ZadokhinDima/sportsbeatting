package com.epam.training.dto;

import lombok.Data;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class SportEventData {

    @Null
    private Long id;
    @NotNull
    private String title;
    @Future
    private LocalDateTime startTime;
    @Future
    private LocalDateTime endTime;
    @NotNull
    private EventType eventType;

    public enum EventType {
        FOOTBALL, TENNIS
    }

}
