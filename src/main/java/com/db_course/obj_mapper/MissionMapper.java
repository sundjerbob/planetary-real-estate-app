package com.db_course.obj_mapper;

import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.MissionDto;
import com.db_course.dto.SpaceshipDto;
import com.db_course.entity_model.Mission;

public class MissionMapper {

    public static MissionDto missionToDto(
            Mission mission,
            CelestialBodyDto celestialBody,
            SpaceshipDto spaceship
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