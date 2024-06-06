package com.db_course.obj_mapper;

import com.db_course.dto.CelestialBodyDto;
import com.db_course.entity_model.CelestialBody;

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
                celestialBody.isSurfaceHard(),
                celestialBody.getMass(),
                celestialBody.getGravitationalFieldHeight(),
                celestialBody.getMovingSpeed(),
                celestialBody.getRotationSpeed()
        );
    }
}
