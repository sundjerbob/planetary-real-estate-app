package com.db_course.obj_mapper;

import com.db_course.dao.CelestialBodyResident;

public class CelestialBodyResidentMapper {


    public static CelestialBodyResident celestialBodyResidentToDto(CelestialBodyResident cbResident) {

        return new CelestialBodyResident(
                cbResident.getId(),
                cbResident.getResidentId(),
                cbResident.getCelestialBodyId(),
                cbResident.getResidentFrom(),
                cbResident.getResidentUntil()
        );
    }


}
