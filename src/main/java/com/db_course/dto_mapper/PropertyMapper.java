package com.db_course.dto_mapper;

import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.PropertyDto;
import com.db_course.entity_model.Property;

public class PropertyMapper {

    public static PropertyDto propertyToDto(Property property, CelestialBodyDto celestialBody) {

        return new PropertyDto(
                property.getId(),
                celestialBody,
                property.getSoldToUserId(),
                property.getPropertyRegNb(),
                property.getAddress(),
                property.getSquareMeters(),
                property.getDescription(),
                property.getName(),
                property.getPrice()
        );
    }
}
