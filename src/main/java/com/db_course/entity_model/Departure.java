package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Departure {

    private int id;
    private int spaceshipId;
    private int celestialPathId;
    private LocalDateTime departureDate;
    private int departureFromBodyId;
}
