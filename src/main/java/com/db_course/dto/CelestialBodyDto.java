package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialBodyDto {

    private int id;
    private String name;
    private CelestialTypeDto type;
    private String description;
    private double surfacePressure;
    private double surfaceTemperature;
    private double coreTemperature;
    private boolean hasBeenExplored;
    private String radiationLevels;
    private boolean hasWater;
    private double surfaceArea;
    private boolean isSurfaceHard;
    private double mass;
    private double gravitationalFieldHeight;
    private String rotatesAroundBody;
    private double movingSpeed;
    private double rotationSpeed;
}
