package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CelestialBody {

    private int id;
    private Integer rotatesAroundId;
    private String name;
    private CelestialType type;
    private String description;
    private BigDecimal surfacePressure;
    private BigDecimal surfaceTemperatureMin;
    private BigDecimal surfaceTemperatureMax;
    private BigDecimal coreTemperature;
    private boolean explored;
    private String radiationLevels;
    private boolean hasWater;
    private BigDecimal surfaceArea;
    private boolean isSurfaceHard;
    private BigDecimal mass;
    private BigDecimal gravitationalFieldHeight;
    private BigDecimal movingSpeed;
    private BigDecimal rotationSpeed;
}
