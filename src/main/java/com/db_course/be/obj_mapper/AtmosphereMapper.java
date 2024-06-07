package com.db_course.be.obj_mapper;

import com.db_course.dto.AtmosphereDto;
import com.db_course.be.entity_model.Atmosphere;

public class AtmosphereMapper {

    public static AtmosphereDto atmosphereToDto(Atmosphere atmosphere) {

        AtmosphereDto dto = new AtmosphereDto();
        dto.setId(atmosphere.getId());
        dto.setAtmosphereHeight(atmosphere.getAtmosphereHeight());
        dto.setMinTemperature(atmosphere.getMinTemperature());
        dto.setMaxTemperature(atmosphere.getMaxTemperature());
        return dto;
    }

}
