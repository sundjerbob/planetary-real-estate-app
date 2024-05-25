package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DepartureDto {

    private int id;
    private LocalDateTime departureDate;
    private String celestialBodyOrigin;
    private String celestialBodyDest;
    private BigDecimal pathwayLengthKm;
}


