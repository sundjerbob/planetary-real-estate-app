package com.db_course.obj_mapper;

import com.db_course.dto.ElementDto;
import com.db_course.entity_model.Element;

public class ElementMapper {

    public static ElementDto elementToDto(Element element) {

        return new ElementDto(
                element.getId(),
                element.getName(),
                element.getMinPercentage(),
                element.getMaxPercentage(),
                element.isRadioactive()
        );
    }
}
