package com.db_course.dto_mapper;

import com.db_course.dto.DepartureDto;
import com.db_course.entity_model.Departure;

import java.math.BigDecimal;

public class DepartureMapper {

    public static DepartureDto departureToDto(Departure departure, String origin, String destination, BigDecimal pathway) {

        return new DepartureDto(
                departure.getId(),
                departure.getDepartureDate(),
                origin,
                destination,
                pathway
        );
    }
}
