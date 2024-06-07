package com.db_course.be.service;

import com.db_course.be.dao.CelestialBodyDao;
import com.db_course.be.entity_model.CelestialBody;
import com.db_course.be.filter.entity_filters.impl.CelestialBodyFilter;
import com.db_course.dto.CelestialBodyDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.CelestialBodyMapper.celestialBodyToDto;

public class CelestialBodyService {


    private static volatile CelestialBodyService instance;
    private static final Object mutex = new Object();
    private final CelestialBodyDao celestialBodyDao;


    private CelestialBodyService() {
        celestialBodyDao = new CelestialBodyDao();

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

        return celestialBody == null ?
                null
                :
                setForeignReferenceAttributes(
                        celestialBodyToDto(celestialBody),
                        celestialBody.getCelestialBodyTypeId(),
                        celestialBody.getRotatesAroundId()
                );

    }


    public void processAllCelestialBodies(Consumer<CelestialBodyDto> consumer) {

        Consumer<CelestialBody> dbObjConsumer = celestialBody ->
        {
            consumer.accept(
                    setForeignReferenceAttributes(
                            celestialBodyToDto(celestialBody),
                            celestialBody.getCelestialBodyTypeId(),
                            celestialBody.getRotatesAroundId()
                    )
            );
        };

        celestialBodyDao.processAllCelestialBodies(dbObjConsumer);
    }

    public void processFilteredCelestialBodies(Consumer<CelestialBodyDto> consumer, CelestialBodyFilter celestialBodyFilter) {

        Consumer<CelestialBody> dbObjConsumer = celestialBody ->
        {
            consumer.accept(
                    setForeignReferenceAttributes(
                            celestialBodyToDto(celestialBody),
                            celestialBody.getCelestialBodyTypeId(),
                            celestialBody.getRotatesAroundId()
                    )
            );
        };

        celestialBodyDao.processFilteredCelestialBodies(dbObjConsumer, celestialBodyFilter);
    }


    public void processCelestialBodiesByTypeId(int typeId, Consumer<CelestialBodyDto> consumer) {

        Consumer<CelestialBody> dbObjConsumer = celestialBody ->
        {

            consumer.accept(
                    setForeignReferenceAttributes(
                            celestialBodyToDto(celestialBody),
                            celestialBody.getCelestialBodyTypeId(),
                            celestialBody.getRotatesAroundId())
            );
        };

        celestialBodyDao.processCelestialBodiesByTypeId(typeId, dbObjConsumer);
    }


    private CelestialBodyDto setForeignReferenceAttributes(CelestialBodyDto dto, int celestialTypeId, Integer rotatesAroundId) {
        String typeName = CelestialTypeService.getInstance().getCelestialTypeById(celestialTypeId).getName();
        String rotatesAroundName =
                rotatesAroundId == null ?
                        null : celestialBodyDao.getCelestialBodyById(rotatesAroundId).getName();
        dto.setCelestialBodyType(typeName);
        dto.setRotatesAroundBody(rotatesAroundName);
        return dto;
    }


}
