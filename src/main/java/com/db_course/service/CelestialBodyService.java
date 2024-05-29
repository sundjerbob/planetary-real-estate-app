package com.db_course.service;

import com.db_course.dao.CelestialBodyDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.CelestialBodyDto;
import com.db_course.entity_model.CelestialBody;
import com.db_course.entity_model.CelestialType;

import java.util.Locale;
import java.util.function.Consumer;

import static com.db_course.obj_mapper.CelestialBodyMapper.celestialBodyToDto;

public class CelestialBodyService {


    private static volatile CelestialBodyService instance;
    private static final Object mutex = new Object();
    private final CelestialBodyDao celestialBodyDao;


    private CelestialBodyService() {
        celestialBodyDao = new CelestialBodyDao(
                DB_Client.getInstance().getConnection()
        );

    }


    public static CelestialBodyService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new CelestialBodyService();
                }
            }
        }
        return instance;
    }


    public CelestialBodyDto getCelestialBodyById(int id) {

        CelestialBody celestialBody = celestialBodyDao.getCelestialBodyById(id);

        if (celestialBody.getRotatesAroundId() == null)
            return celestialBodyToDto(celestialBody, null);

        CelestialBody rotatesAround = celestialBodyDao.getCelestialBodyById(celestialBody.getRotatesAroundId());

        return celestialBodyToDto(celestialBodyDao.getCelestialBodyById(id), rotatesAround.getName());
    }


    public void processAllCelestialBodies(Consumer<CelestialBodyDto> consumer) {

        Consumer<CelestialBody> dbObjConsumer = celestialBody -> {

            String rotatesAroundObj = null;
            Integer rotatesAroundId = celestialBody.getRotatesAroundId();

            if (rotatesAroundId != null)
                rotatesAroundObj = celestialBodyDao.getCelestialBodyById(celestialBody.getRotatesAroundId()).getName();

            CelestialBodyDto dto = celestialBodyToDto(celestialBody, rotatesAroundObj);
            consumer.accept(dto);
        };

        celestialBodyDao.processAllCelestialBodies(dbObjConsumer);
    }


    public void processCelestialBodiesByType(String celestialType, Consumer<CelestialBodyDto> consumer) {
        CelestialType type = CelestialType.valueOf(celestialType.toUpperCase(Locale.ENGLISH));

        Consumer<CelestialBody> dbObjConsumer = celestialBody -> {

            String rotatesAround = null;
            Integer rotatesAroundId = celestialBody.getRotatesAroundId();

            if (rotatesAroundId != null && rotatesAroundId > 0)
                rotatesAround = celestialBodyDao.getCelestialBodyById(rotatesAroundId).getName();


            CelestialBodyDto dto = celestialBodyToDto(celestialBody, rotatesAround);
            consumer.accept(dto);
        };

        celestialBodyDao.processCelestialBodiesByType(type, dbObjConsumer);
    }


}
