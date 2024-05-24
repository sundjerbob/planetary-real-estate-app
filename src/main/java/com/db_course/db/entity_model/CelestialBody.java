package com.db_course.db.entity_model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CelestialBody {
    private int celestialBodyId;
    private String name;
    private CelestialType type;
    private String description;
}