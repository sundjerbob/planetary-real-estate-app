package com.db_course.be.obj_mapper;

import com.db_course.dto.ElementDto;
import com.db_course.be.entity_model.Element;

public class ElementMapper {

    public static ElementDto elementToDto(Element element) {

        return new ElementDto(
                element.getId(),
                element.getName(),
                element.getDescription(),
                element.getMinPercentage(),
                element.getMaxPercentage(),
                element.isRadioactive(),
                element.isInert()
        );
    }
}
