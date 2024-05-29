package com.db_course.service;

import com.db_course.dao.SpaceshipDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.SpaceshipDto;
import com.db_course.entity_model.Spaceship;

import static com.db_course.obj_mapper.SpaceshipMapper.spaceshipToDto;

public class SpaceshipService {
    private static volatile SpaceshipService instance;
    private static final Object mutex = new Object();
    private final SpaceshipDao spaceshipDao;


    private SpaceshipService() {
        spaceshipDao = new SpaceshipDao(
                DB_Client.getInstance().getConnection()
        );
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
