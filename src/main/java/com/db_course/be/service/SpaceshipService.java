package com.db_course.be.service;

import com.db_course.be.dao.SpaceshipDao;
import com.db_course.be.entity_model.Spaceship;
import com.db_course.be.filter.entity_filters.impl.SpaceshipFilter;
import com.db_course.dto.SpaceshipDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.SpaceshipMapper.spaceshipToDto;

public class SpaceshipService {
    private static volatile SpaceshipService instance;
    private static final Object mutex = new Object();
    private final SpaceshipDao spaceshipDao;


    private SpaceshipService() {
        spaceshipDao = new SpaceshipDao();
    }


    public static SpaceshipService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new SpaceshipService();
                }
            }
        }
        return instance;
    }

    public SpaceshipDto getSpaceshipById(int spaceshipId) {
        Spaceship spaceship = spaceshipDao.getSpaceshipById(spaceshipId);
        return spaceshipToDto(spaceship);
    }

    public void processAllSpaceships(Consumer<SpaceshipDto> consumer) {
        spaceshipDao.processAllSpaceships(spaceship -> consumer.accept(spaceshipToDto(spaceship)));
    }

    public void processFilteredSpaceships(Consumer<SpaceshipDto> consumer, SpaceshipFilter filter) {
        spaceshipDao.processFilteredSpaceships(spaceship -> consumer.accept(spaceshipToDto(spaceship)), filter);
    }

}
