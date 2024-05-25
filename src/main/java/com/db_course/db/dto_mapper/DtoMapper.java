package com.db_course.db.dto_mapper;

import com.db_course.db.entity_model.*;
import com.db_course.dto.*;

import java.math.BigDecimal;

public class DtoMapper {


    public static UserDto userToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }


    public static DepartureDto departureToDto(
            Departure departure,
            String origin,
            String destination,
            BigDecimal pathway
    ) {

        return new DepartureDto(
                departure.getId(),
                departure.getDepartureDate(),
                origin,
                destination,
                pathway
        );
    }


    public static CelestialPathwayDto celestialPathwayToDto(CelestialPathway celestialPathway, String bodyA, String bodyB) {
        return new CelestialPathwayDto(
                celestialPathway.getId(),
                bodyA,
                bodyB,
                celestialPathway.getDistanceKm()
        );
    }

    public static CelestialBodyDto celestialBodyToDto(CelestialBody celestialBody, String rotatesAround) {

        return new CelestialBodyDto(
                celestialBody.getId(),
                celestialBody.getName(),
                celestialTypeToDto(celestialBody.getType()),
                celestialBody.getDescription(),
                celestialBody.getSurfacePressure(),
                celestialBody.getSurfaceTemperatureMin(),
                celestialBody.getSurfaceTemperatureMax(),
                celestialBody.getCoreTemperature(),
                celestialBody.isHasBeenExplored(),
                celestialBody.getRadiationLevels(),
                celestialBody.isHasWater(),
                celestialBody.getSurfaceArea(),
                celestialBody.isSurfaceHard(),
                celestialBody.getMass(),
                celestialBody.getGravitationalFieldHeight(),
                // *********************
                rotatesAround,
                // *********************
                celestialBody.getMovingSpeed(),
                celestialBody.getRotationSpeed()
        );
    }

    static public CelestialTypeDto celestialTypeToDto(CelestialType celestialType) {

        return new CelestialTypeDto(
                celestialType.ordinal() + 1,
                celestialType.name(),
                celestialType.getDescription()
        );

    }
}
