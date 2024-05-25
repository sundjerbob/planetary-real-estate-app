package com.db_course.dto_mapper;

import com.db_course.dto.CelestialPathDto;
import com.db_course.entity_model.CelestialPath;

public class CelestialPathMapper {


    public static CelestialPathDto celestialPathToDto(CelestialPath celestialPath) {

        return new CelestialPathDto(
                celestialPath.getId(),
                celestialPath.getBodyA_Id(),
                celestialPath.getBodyB_Id(),
                celestialPath.getDistanceKm()
        );
    }

}
