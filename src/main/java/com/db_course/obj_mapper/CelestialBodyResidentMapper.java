package com.db_course.obj_mapper;

import com.db_course.dto.CelestialBodyResidentDto;
import com.db_course.entity_model.CelestialBodyResident;

public class CelestialBodyResidentMapper {


    public static CelestialBodyResidentDto celestialBodyResidentToDto(CelestialBodyResident cbResident, String celestialBody) {

        return new CelestialBodyResidentDto(
                cbResident.getId(),
                cbResident.getResidentId(),
                celestialBody,
                cbResident.getResidentFrom(),
                cbResident.getResidentUntil()
        );
    }


}
