package com.db_course.be.obj_mapper;

import com.db_course.dto.SpaceshipDto;
import com.db_course.be.entity_model.Spaceship;

public class SpaceshipMapper {

    public static SpaceshipDto spaceshipToDto(Spaceship spaceship) {
        return new SpaceshipDto(
                spaceship.getId(),
                spaceship.getName(),
                spaceship.getModel(),
                spaceship.getPassengerCapacity(),
                spaceship.getFuelCapacity(),
                spaceship.getMaxTravelRange(),
                spaceship.getTravelingSpeed(),
                spaceship.getManufacturer()
        );

    }
}
