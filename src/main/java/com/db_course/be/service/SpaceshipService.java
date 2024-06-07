package com.db_course.be.service;

import com.db_course.be.dao.SpaceshipDao;
import com.db_course.dto.SpaceshipDto;
import com.db_course.be.entity_model.Spaceship;

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

}
