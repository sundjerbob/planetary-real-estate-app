package com.db_course.dto_mapper;

import com.db_course.dto.MissionDto;
import com.db_course.entity_model.Mission;

public class MissionMapper {

    public static MissionDto missionToDto(Mission mission) {
        return new MissionDto(
                mission.getId(),
                mission.getName(),
                mission.getLaunchDate(),
                mission.getCelestialBodyId()
        );
    }
}
