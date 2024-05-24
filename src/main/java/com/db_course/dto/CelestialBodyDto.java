package com.db_course.dto;

import com.db_course.db.entity_model.CelestialType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialBodyDto {

    private int celestialBodyId;
    private String name;
    private CelestialType type;
    private String description;
}
