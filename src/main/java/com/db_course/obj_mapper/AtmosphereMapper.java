package com.db_course.obj_mapper;

import com.db_course.dto.AtmosphereDto;
import com.db_course.entity_model.Atmosphere;

public class AtmosphereMapper {

    public static AtmosphereDto atmosphereToDto(Atmosphere atmosphere) {

        return new AtmosphereDto(
                atmosphere.getId(),
                atmosphere.getMaxTemperature(),
                atmosphere.getMinTemperature(),
                atmosphere.getAtmosphereHeight(),
                atmosphere.getAmperePressure()
        );
    }

}
