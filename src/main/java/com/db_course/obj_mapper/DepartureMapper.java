package com.db_course.obj_mapper;

import com.db_course.dto.CelestialPathDto;
import com.db_course.dto.DepartureDto;
import com.db_course.dto.SpaceshipDto;
import com.db_course.entity_model.Departure;

public class DepartureMapper {

    public static DepartureDto departureToDto(
            Departure departure,
            SpaceshipDto spaceship,
            CelestialPathDto celestialPath,
            long travelDurationDays
    ) {

        return new DepartureDto(
                departure.getId(),
                spaceship,
                celestialPath,
                departure.getDepartureDate(),
                travelDurationDays
        );
    }
}
