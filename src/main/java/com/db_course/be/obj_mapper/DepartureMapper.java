package com.db_course.be.obj_mapper;

import com.db_course.be.entity_model.Departure;
import com.db_course.dto.DepartureDto;

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
                celestialOrigin,
                celestialDestination,
                spaceship
        );
    }
}
