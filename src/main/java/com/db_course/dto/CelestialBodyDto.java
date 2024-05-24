package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialBodyDto {

    private int id;
    private String name;
    private CelestialTypeDto type;
    private String description;
    private BigDecimal surfacePressure;
    private BigDecimal surfaceTemperatureMin;
    private BigDecimal surfaceTemperatureMax;
    private BigDecimal coreTemperature;
    private boolean hasBeenExplored;
    private String radiationLevels;
    private boolean hasWater;
    private double surfaceArea;
    private boolean isSurfaceHard;
    private BigDecimal mass;
    private BigDecimal gravitationalFieldHeight;
    private String rotatesAroundBody;
    private BigDecimal movingSpeed;
    private BigDecimal rotationSpeed;
}
