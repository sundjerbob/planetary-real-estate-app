package com.db_course.service;

import com.db_course.dao.CelestialPathDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.CelestialPathDto;
import com.db_course.entity_model.CelestialPath;

import java.util.function.Consumer;

import static com.db_course.dto_mapper.CelestialPathMapper.celestialPathToDto;

public class CelestialPathService {

    private static CelestialPathService instance;
    private static final Object mutex = new Object();
    private final CelestialPathDao celestialPathDao;


    private CelestialPathService() {
        celestialPathDao = new CelestialPathDao(
                DB_Client.getInstance().getConnection()
        );
    }


    public static CelestialPathService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new CelestialPathService();
                }
            }
        }
        return instance;
    }


    public void processAllPaths(Consumer<CelestialPath> pathConsumer) {
        celestialPathDao.processAllPaths(pathConsumer);
    }


    public void processPathsByBodyId(int bodyId, Consumer<CelestialPathDto> consumer) {

        Consumer<CelestialPath> dbObjConsumer =
                path -> {
                    CelestialPathDto celestialPathDto = celestialPathToDto(path);
                    consumer.accept(celestialPathDto);
                };

        celestialPathDao.processPathsByBodyId(bodyId, dbObjConsumer);
    }

    public CelestialPathDto getCelestialPathByBodyIds(int bodyA, int bodyB) {


        CelestialPath path = celestialPathDao.getPathByBodyIds(bodyA, bodyB);
        return path == null ? null : celestialPathToDto(path);
    }


}
