package com.db_course.service;

import com.db_course.dao.CelestialTypeDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.CelestialTypeDto;
import com.db_course.entity_model.CelestialType;

import java.util.function.Consumer;

import static com.db_course.obj_mapper.CelestialTypeMapper.celestialTypeToDto;

public class CelestialTypeService {


    private static volatile CelestialTypeService instance;
    private static final Object mutex = new Object();
    private CelestialTypeDao celestialTypeDao;


    private CelestialTypeService() {
        celestialTypeDao = new CelestialTypeDao();
    }


    public static CelestialTypeService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new CelestialTypeService();
                }
            }
        }
        return instance;
    }

    public void processAllCelestialTypes(Consumer<CelestialTypeDto> consumer) {

        Consumer<CelestialType> dbObjConsumer = celestialType -> {
            CelestialTypeDto celestialTypeDto = celestialTypeToDto(celestialType);
            consumer.accept(celestialTypeDto);
        };

        celestialTypeDao.processAllCelestialTypes(dbObjConsumer);
    }

    public CelestialTypeDto getCelestialTypeById(int id) {

        CelestialType type = celestialTypeDao.getCelestialTypeById(id);
        return type == null ? null : celestialTypeToDto(type);
    }

}
