package com.db_course.dto_mapper;

import com.db_course.dto.CelestialBodyDto;
import com.db_course.entity_model.CelestialBody;

import static com.db_course.dto_mapper.CelestialTypeMapper.celestialTypeToDto;

public class CelestialBodyMapper {

    public static CelestialBodyDto celestialBodyToDto(CelestialBody celestialBody, String rotatesAround) {

        return new CelestialBodyDto(
                celestialBody.getId(),
                rotatesAround,
                celestialBody.getName(),
                celestialTypeToDto(celestialBody.getType()),
                celestialBody.getDescription(),
                celestialBody.getSurfacePressure(),
                celestialBody.getSurfaceTemperatureMin(),
                celestialBody.getSurfaceTemperatureMax(),
                celestialBody.getCoreTemperature(),
                celestialBody.isExplored(),
                celestialBody.getRadiationLevels(),
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
