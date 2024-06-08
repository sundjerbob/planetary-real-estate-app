package com.db_course.be.service;


import com.db_course.be.dao.CelestialBodyResidentDao;
import com.db_course.be.entity_model.CelestialBodyResident;
import com.db_course.be.filter.entity_filters.impl.CelestialBodyResidentFilter;
import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.CelestialBodyResidentDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.CelestialBodyResidentMapper.celestialBodyResidentToDto;

public class CelestialBodyResidentService {


    private static volatile CelestialBodyResidentService instance;
    private static final Object mutex = new Object();
    private final CelestialBodyResidentDao atmosphereDao;


    private CelestialBodyResidentService() {
        atmosphereDao = new CelestialBodyResidentDao();
    }

    /******************************************************************************************************************/
    public static CelestialBodyResidentService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new CelestialBodyResidentService();
                }
            }
        }
        return instance;
    }


    public void processAllCelestialBodyResidents(Consumer<CelestialBodyResidentDto> consumer) {

        Consumer<CelestialBodyResident> dbObjConsumer = resident -> {
            CelestialBodyDto residesAt = CelestialBodyService.getInstance().getCelestialBodyById(resident.getCelestialBodyId());
            if (residesAt == null)
                throw new RuntimeException("CelestialBodyResidentDao.processAllCelestialBodyResidents() residesAt is null");
            consumer.accept(celestialBodyResidentToDto(resident, residesAt.getName()));
        };
        atmosphereDao.processAllCelestialBodyResidents(dbObjConsumer);
    }

    public void processFilteredCelestialBodyResidents(Consumer<CelestialBodyResidentDto> consumer, CelestialBodyResidentFilter filter) {

        Consumer<CelestialBodyResident> dbObjConsumer = resident -> {
            CelestialBodyDto residesAt = CelestialBodyService.getInstance().getCelestialBodyById(resident.getCelestialBodyId());
            if (residesAt == null)
                throw new RuntimeException("CelestialBodyResidentDao.processAllCelestialBodyResidents() residesAt is null");
            consumer.accept(celestialBodyResidentToDto(resident, residesAt.getName()));
        };
        atmosphereDao.processFilteredCelestialBodyResidents(dbObjConsumer, filter);
    }
}
