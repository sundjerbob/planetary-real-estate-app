package com.db_course.dto_mapper;

import com.db_course.dto.CelestialTypeDto;
import com.db_course.entity_model.CelestialType;

public class CelestialTypeMapper {


    static public CelestialTypeDto celestialTypeToDto(CelestialType celestialType) {

        return new CelestialTypeDto(
                celestialType.ordinal() + 1,
                celestialType.name(),
                celestialType.getDescription()
        );

    }
}
