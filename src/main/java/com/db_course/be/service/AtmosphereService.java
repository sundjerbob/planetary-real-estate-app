package com.db_course.be.service;

import com.db_course.be.dao.AtmosphereDao;
import com.db_course.be.filter.entity_filters.impl.AtmosphereFilter;
import com.db_course.dto.AtmosphereDto;
import com.db_course.be.entity_model.Atmosphere;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.AtmosphereMapper.atmosphereToDto;

public class AtmosphereService {


    private static volatile AtmosphereService instance;
    private static final Object mutex = new Object();
    private final AtmosphereDao atmosphereDao;


    private AtmosphereService() {
        atmosphereDao = new AtmosphereDao();
    }

    /******************************************************************************************************************/
    public static AtmosphereService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new AtmosphereService();
                }
            }
        }
        return instance;
    }

    /******************************************************************************************************************/
    public AtmosphereDto getAtmosphereByCelestialBodyId(int celestialBodyId) {
        Atmosphere atmosphere = atmosphereDao.getAtmosphereByCelestialBodyId(celestialBodyId);
        return setForeignAttributes(atmosphereToDto(atmosphere), atmosphere.getCelestialBodyId());
    }

    public void processAllAtmospheres(Consumer<AtmosphereDto> consumer) {
        Consumer<Atmosphere> dbObjConsumer = atmosphere -> {
            consumer.accept(
                    setForeignAttributes(
                            atmosphereToDto(atmosphere),
                            atmosphere.getCelestialBodyId()
                    )
            );
        };
        atmosphereDao.processAllAtmospheres(dbObjConsumer);
    }

    public void processFilteredAtmospheres(Consumer<AtmosphereDto> consumer, AtmosphereFilter filter) {
        Consumer<Atmosphere> dbObjConsumer = atmosphere -> {
            consumer.accept(
                    setForeignAttributes(
                            atmosphereToDto(atmosphere),
                            atmosphere.getCelestialBodyId()
                    )
            );
        };
        atmosphereDao.processFilteredAtmospheres(dbObjConsumer, filter);
    }

    private AtmosphereDto setForeignAttributes(AtmosphereDto dto, int celestialBodyId) {
        String cbName = CelestialBodyService.getInstance().getCelestialBodyById(celestialBodyId).getName();
        dto.setCelestialBody(cbName);
        return dto;
    }


}
