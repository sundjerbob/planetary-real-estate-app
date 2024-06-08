package com.db_course.be.service;

import com.db_course.be.dao.MissionDao;
import com.db_course.be.filter.entity_filters.impl.MissionFilter;
import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.MissionDto;
import com.db_course.dto.SpaceshipDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.MissionMapper.missionToDto;

public class MissionService {
    private static volatile MissionService instance;
    private static final Object mutex = new Object();
    private final MissionDao celestialPathDao;


    private MissionService() {
        celestialPathDao = new MissionDao();
    }


    public static MissionService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new MissionService();
                }
            }
        }
        return instance;
    }


    public void processAllMissions(Consumer<MissionDto> consumer) {
        celestialPathDao.processAllMissions(
                mission -> {
                    CelestialBodyDto visitedBody = CelestialBodyService.getInstance().getCelestialBodyById(mission.getExploredBodyId());
                    SpaceshipDto spaceship = SpaceshipService.getInstance().getSpaceshipById(mission.getSpaceshipId());
                    consumer.accept(missionToDto(mission, visitedBody.getName(), spaceship.getName()));
                }
        );

    }

    public void processFilteredMissions(Consumer<MissionDto> consumer, MissionFilter filter) {
        celestialPathDao.processFilteredMissions(
                mission -> {
                    CelestialBodyDto visitedBody = CelestialBodyService.getInstance().getCelestialBodyById(mission.getExploredBodyId());
                    SpaceshipDto spaceship = SpaceshipService.getInstance().getSpaceshipById(mission.getSpaceshipId());
                    consumer.accept(missionToDto(mission, visitedBody.getName(), spaceship.getName()));
                },
                filter
        );


    }
}
