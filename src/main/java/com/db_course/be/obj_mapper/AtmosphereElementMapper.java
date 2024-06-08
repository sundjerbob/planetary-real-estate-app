package com.db_course.be.obj_mapper;

import com.db_course.be.entity_model.AtmosphereElement;
import com.db_course.dto.AtmosphereElementDto;

public class AtmosphereElementMapper {


    public static AtmosphereElementDto atmosphereElementToDto(AtmosphereElement atmosphereElement, String celestialBody, String element) {

        return new AtmosphereElementDto(
                atmosphereElement.getAtmosphereId(),
                celestialBody,
                element,
                atmosphereElement.getPercentage()
        );

    }

}
