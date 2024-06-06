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
    private String description;
    private BigDecimal surfacePressure;
    private BigDecimal surfaceTemperatureMin;
    private BigDecimal surfaceTemperatureMax;
    private BigDecimal coreTemperature;
    private boolean explored;
    private String radiationLevel;
    private boolean hasWater;
    private BigDecimal surfaceArea;
    private boolean isSurfaceHard;
    private BigDecimal mass;
    private BigDecimal gravitationalFieldHeight;
    private BigDecimal movingSpeed;
    private BigDecimal rotationSpeed;

    private String celestialBodyType;
    private String rotatesAroundBody;

    public CelestialBodyDto(
            int id,
            String name,
            String description,
            BigDecimal surfacePressure,
            BigDecimal surfaceTemperatureMin,
            BigDecimal surfaceTemperatureMax,
            BigDecimal coreTemperature,
            boolean explored,
            String radiationLevel,
            boolean hasWater,
            BigDecimal surfaceArea,
            boolean isSurfaceHard,
            BigDecimal mass,
            BigDecimal gravitationalFieldHeight,
            BigDecimal movingSpeed,
            BigDecimal rotationSpeed
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.surfacePressure = surfacePressure;
        this.surfaceTemperatureMin = surfaceTemperatureMin;
        this.surfaceTemperatureMax = surfaceTemperatureMax;
        this.coreTemperature = coreTemperature;
        this.explored = explored;
        this.radiationLevel = radiationLevel;
        this.hasWater = hasWater;
        this.surfaceArea = surfaceArea;
        this.isSurfaceHard = isSurfaceHard;
        this.mass = mass;
        this.gravitationalFieldHeight = gravitationalFieldHeight;
        this.movingSpeed = movingSpeed;
        this.rotationSpeed = rotationSpeed;
    }
}
