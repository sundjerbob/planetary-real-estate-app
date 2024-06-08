package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Departure {

    private int id;
    private LocalDateTime departureDate;
    private int celestialBodyOriginId;
    private int celestialBodyDestinationId;
    private int spaceshipId;
}
