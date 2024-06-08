package com.db_course.be.obj_mapper;

import com.db_course.be.entity_model.Property;
import com.db_course.dto.PropertyDto;

public class PropertyMapper {

    public static PropertyDto propertyToDto(Property property, String celestialBody, String soldToUser) {

        return new PropertyDto(
                property.getId(),
                celestialBody,
                soldToUser,
                property.getPropertyRegNb(),
                property.getAddress(),
                property.getSquareMeters(),
                property.getDescription(),
                property.getName(),
                property.getPrice()
        );
    }
}
