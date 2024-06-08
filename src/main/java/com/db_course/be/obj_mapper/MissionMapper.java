package com.db_course.be.obj_mapper;

import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.MissionDto;
import com.db_course.dto.SpaceshipDto;
import com.db_course.be.entity_model.Mission;

public class MissionMapper {

    public static MissionDto missionToDto(
            Mission mission,
            String celestialBody,
            String spaceship
    ) {
        return new MissionDto(
                mission.getId(),
                celestialBody,
                spaceship,
                mission.getName(),
                mission.getDescription(),
                mission.getStartDate(),
                mission.getEndDate(),
                mission.isCompleted()

        );
    }
}
