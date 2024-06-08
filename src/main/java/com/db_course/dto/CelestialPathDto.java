package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialPathDto {

    private int id;
    private String bodyA;
    private String bodyB;
    private BigDecimal distanceKm;
    private String description;

}
