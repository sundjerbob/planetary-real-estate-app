package com.db_course.db.obj_mapper;

import com.db_course.db.entity_model.*;
import com.db_course.dto.*;

public class DtoMapper {


    public static UserDto userToDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        return userDto;
    }


    static DepartureDto departureToDto(
            Departure departure,
            CelestialBody origin,
            CelestialBody destination,
            CelestialPathway pathway
    ) {

        return new DepartureDto(
                departure.getId(),
                departure.getDepartureDate(),
                origin.getName(),
                destination.getName(),
                pathway.getDistanceKm()
        );
    }


    static CelestialPathwayDto celestialPathwayToDto(CelestialPathway celestialPathway, CelestialBody bodyA, CelestialBody bodyB) {
        return new CelestialPathwayDto(
                celestialPathway.getId(),
                bodyA.getName(),
                bodyB.getName(),
                celestialPathway.getDistanceKm()

        );
    }

    static CelestialBodyDto celestialBodyToDto(CelestialBody celestialBody, CelestialBody rotatesAround) {

        return new CelestialBodyDto(
                celestialBody.getId(),
                celestialBody.getName(),
                celestialTypeToDto(celestialBody.getType()),
                celestialBody.getDescription(),
                celestialBody.getSurfacePressure(),
                celestialBody.getSurfaceTemperature(),
                celestialBody.getCoreTemperature(),
                celestialBody.isHasBeenExplored(),
                celestialBody.getRadiationLevels(),
                celestialBody.isHasWater(),
                celestialBody.getSurfaceArea(),
                celestialBody.isSurfaceHard(),
                celestialBody.getMass(),
                celestialBody.getGravitationalFieldHeight(),
                // *********************
                rotatesAround.getName(),
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
