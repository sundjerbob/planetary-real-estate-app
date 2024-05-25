package com.db_course.dto_mapper;

import com.db_course.dto.CelestialPathDto;
import com.db_course.entity_model.CelestialPath;

public class CelestialPathMapper {


    public static CelestialPathDto celestialPathwayToDto(CelestialPath celestialPath, String bodyA, String bodyB) {
        return new CelestialPathDto(
                celestialPath.getId(),
                bodyA,
                bodyB,
                celestialPath.getDistanceKm()
        );
    }
}
