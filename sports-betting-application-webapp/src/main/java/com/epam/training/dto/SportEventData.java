package com.epam.training.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
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

    private List<BetData> bets;

    public enum EventType {
        FOOTBALL, TENNIS
    }

}
