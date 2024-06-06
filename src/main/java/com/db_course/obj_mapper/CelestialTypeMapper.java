package com.db_course.obj_mapper;

import com.db_course.dto.CelestialTypeDto;
import com.db_course.entity_model.CelestialType;

public class CelestialTypeMapper {


    static public CelestialTypeDto celestialTypeToDto(CelestialType celestialType) {

        return new CelestialTypeDto(celestialType.getId(), celestialType.getName(), celestialType.getDescription());

    }
}
