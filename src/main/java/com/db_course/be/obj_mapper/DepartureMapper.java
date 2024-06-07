package com.db_course.be.obj_mapper;

import com.db_course.dto.DepartureDto;
import com.db_course.be.entity_model.Departure;

public class DepartureMapper {

    public static DepartureDto departureToDto(
            Departure departure,
            String spaceship,
            String celestialOrigin,
            String celestialDestination,
            long travelDurationDays
    ) {

        return new DepartureDto(
                departure.getId(),
                departure.getDepartureDate(),
                travelDurationDays,
                spaceship,
                celestialOrigin,
                celestialDestination

        );
    }
}
