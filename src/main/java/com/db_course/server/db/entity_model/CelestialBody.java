package com.db_course.server.db.entity_model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CelestialBody {
    private int celestialBodyId;
    private String name;
    private int type;
    private String description;
}