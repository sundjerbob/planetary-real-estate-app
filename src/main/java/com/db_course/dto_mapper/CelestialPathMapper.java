package com.db_course.dto_mapper;

import com.db_course.dto.CelestialPathDto;
import com.db_course.entity_model.CelestialPath;

public class CelestialPathMapper {


    public static CelestialPathDto celestialPathToDto(CelestialPath celestialPath, String bodyOrigin, String bodyDestination) {

        return new CelestialPathDto(
                celestialPath.getId(),
                bodyOrigin,
                bodyDestination,
                celestialPath.getDistanceKm(),
                celestialPath.getDescription()
        );
    }

}
