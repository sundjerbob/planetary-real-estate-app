package com.db_course.be.service;

import com.db_course.be.dao.CelestialTypeDao;
import com.db_course.be.entity_model.CelestialType;
import com.db_course.be.filter.entity_filters.impl.CelestialTypeFilter;
import com.db_course.dto.CelestialTypeDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.CelestialTypeMapper.celestialTypeToDto;

public class CelestialTypeService {


    private static volatile CelestialTypeService instance;
    private static final Object mutex = new Object();
    private final CelestialTypeDao celestialTypeDao;


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


    public void processFilteredCelestialTypes(Consumer<CelestialTypeDto> consumer, CelestialTypeFilter filter) {

        Consumer<CelestialType> dbObjConsumer = celestialType -> {
            CelestialTypeDto celestialTypeDto = celestialTypeToDto(celestialType);
            consumer.accept(celestialTypeDto);
        };

        celestialTypeDao.processFilteredCelestialTypes(dbObjConsumer, filter);
    }

    public CelestialTypeDto getCelestialTypeById(int id) {

        CelestialType type = celestialTypeDao.getCelestialTypeById(id);
        return type == null ? null : celestialTypeToDto(type);
    }

}
