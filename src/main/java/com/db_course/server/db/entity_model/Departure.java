package com.db_course.server.db.entity_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Departure {
    private int departureId;
    private LocalDateTime departureDate;
    private int passengerId;
}
