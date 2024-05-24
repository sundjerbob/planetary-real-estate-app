package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialPathwayDto {

    private int id;
    private String bodyA;
    private String bodyB;
    private double distance_km;

}
