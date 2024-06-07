package com.db_course.be.service;

import com.db_course.be.dao.CelestialPathDao;
import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.CelestialPathDto;
import com.db_course.be.entity_model.CelestialPath;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.CelestialPathMapper.celestialPathToDto;


public class CelestialPathService {


    private static volatile CelestialPathService instance;
    private static final Object mutex = new Object();
    private final CelestialPathDao celestialPathDao;


    private CelestialPathService() {
        celestialPathDao = new CelestialPathDao();
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


    public void processAllPaths(Consumer<CelestialPathDto> consumer) {
        celestialPathDao.processAllPaths(
                path -> {
                    CelestialBodyDto bodyA = CelestialBodyService.getInstance().getCelestialBodyById(path.getBodyA_Id());
                    CelestialBodyDto bodyB = CelestialBodyService.getInstance().getCelestialBodyById(path.getBodyB_Id());
                    CelestialPathDto pathDto = celestialPathToDto(path, bodyA.getName(), bodyB.getName());
                    consumer.accept(pathDto);
                }
        );
    }


    public void processPathsByBodyId(int bodyId, Consumer<CelestialPathDto> consumer) {

        Consumer<CelestialPath> dbObjConsumer =
                path -> {
                    CelestialBodyDto bodyA = CelestialBodyService.getInstance().getCelestialBodyById(path.getBodyA_Id());
                    CelestialBodyDto bodyB = CelestialBodyService.getInstance().getCelestialBodyById(path.getBodyB_Id());
                    CelestialPathDto celestialPathDto = celestialPathToDto(path, bodyA.getName(), bodyB.getName());
                    consumer.accept(celestialPathDto);
                };

        celestialPathDao.processPathsByBodyId(bodyId, dbObjConsumer);
    }


    public CelestialPathDto getCelestialPathByBodyIds(int bodyA_Id, int bodyB_Id) {

        CelestialPath path = celestialPathDao.getPathByBodyIds(bodyA_Id, bodyB_Id);

        if (path == null)
            return null;

        CelestialBodyDto bodyA = CelestialBodyService.getInstance().getCelestialBodyById(path.getBodyA_Id());
        CelestialBodyDto bodyB = CelestialBodyService.getInstance().getCelestialBodyById(path.getBodyB_Id());

        return celestialPathToDto(path, bodyA.getName(), bodyB.getName());
    }


}
