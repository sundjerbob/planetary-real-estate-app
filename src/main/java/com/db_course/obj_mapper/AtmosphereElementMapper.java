package com.db_course.obj_mapper;

import com.db_course.dto.AtmosphereElementDto;
import com.db_course.dto.ElementDto;
import com.db_course.entity_model.AtmosphereElement;

public class AtmosphereElementMapper {


    public static AtmosphereElementDto atmosphereElementToDto(AtmosphereElement atmosphereElement, ElementDto element) {
        return new AtmosphereElementDto(
                atmosphereElement.getAtmosphereId(),
                element,
                atmosphereElement.getPercentage()
        );

    }

}
