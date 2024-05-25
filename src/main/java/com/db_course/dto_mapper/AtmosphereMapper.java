package com.db_course.dto_mapper;

import com.db_course.entity_model.Atmosphere;
import com.db_course.dto.AtmosphereDto;

public class AtmosphereMapper {

    public static AtmosphereDto atmosphereToDto(Atmosphere atmosphere) {

        return new AtmosphereDto(
                atmosphere.getId(),
                atmosphere.getCelestialBodyId(),
                atmosphere.getAtmosphereHeight()
        );
    }

}
