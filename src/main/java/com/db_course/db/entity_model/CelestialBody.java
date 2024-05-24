package com.db_course.db.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CelestialBody {

    private int id;
    private String name;
    private CelestialType type;
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
    private int rotatesAroundId;
    private double movingSpeed;
    private double rotationSpeed;
}
