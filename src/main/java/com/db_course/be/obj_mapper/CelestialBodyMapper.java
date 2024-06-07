package com.db_course.be.obj_mapper;

import com.db_course.dto.CelestialBodyDto;
import com.db_course.be.entity_model.CelestialBody;

public class CelestialBodyMapper {

    public static CelestialBodyDto celestialBodyToDto(CelestialBody celestialBody) {

        return new CelestialBodyDto(
                celestialBody.getId(),
                celestialBody.getName(),
                celestialBody.getDescription(),
                celestialBody.getSurfacePressure(),
                celestialBody.getSurfaceTemperatureMin(),
                celestialBody.getSurfaceTemperatureMax(),
                celestialBody.getCoreTemperature(),
                celestialBody.isExplored(),
                celestialBody.getRadiationLevel().val,
                celestialBody.isHasWater(),
                celestialBody.getSurfaceArea(),
                celestialBody.getMass(),
                celestialBody.getGravitationalFieldHeight(),
                celestialBody.getMovingSpeed(),
                celestialBody.getRotationSpeed()
        );
    }
}
