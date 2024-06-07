package com.db_course.be.entity_model;

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
    private int celestialBodyTypeId;
    private String description;
    private BigDecimal surfacePressure;
    private BigDecimal surfaceTemperatureMin;
    private BigDecimal surfaceTemperatureMax;
    private BigDecimal coreTemperature;
    private boolean explored;
    private RadiationLevel radiationLevel;
    private boolean hasWater;
    private BigDecimal surfaceArea;
    private BigDecimal mass;
    private BigDecimal gravitationalFieldHeight;
    private BigDecimal movingSpeed;
    private BigDecimal rotationSpeed;
}
