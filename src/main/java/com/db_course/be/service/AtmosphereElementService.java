package com.db_course.be.service;

import com.db_course.be.dao.AtmosphereElementDao;
import com.db_course.be.entity_model.AtmosphereElement;
import com.db_course.be.filter.entity_filters.impl.AtmosphereElementFilter;
import com.db_course.dto.AtmosphereDto;
import com.db_course.dto.AtmosphereElementDto;
import com.db_course.dto.ElementDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.AtmosphereElementMapper.atmosphereElementToDto;

public class AtmosphereElementService {


    private static volatile AtmosphereElementService instance;
    private static final Object mutex = new Object();
    private final AtmosphereElementDao atmosphereElementDao;


    private AtmosphereElementService() {
        atmosphereElementDao = new AtmosphereElementDao(
        );
    }

    /****************************************************************************************************************/
    public static AtmosphereElementService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new AtmosphereElementService();
                }
            }
        }
        return instance;
    }


//    /****************************************************************************************************************/
//    public void processAtmosphereElementByAtmosphereId(int atmosphereId, Consumer<AtmosphereElementDto> consumer) {
//        Consumer<AtmosphereElement> dbObjConsumer = atmosphereElement -> {
//
//            ElementDto elementDto = ElementService.getInstance().getElementById(atmosphereElement.getElementId());
//            AtmosphereDto atmosphere = AtmosphereService.getInstance().getAtmosphereByCelestialBodyId(atmosphereElement.getAtmosphereId());
//            AtmosphereElementDto dto = atmosphereElementToDto(atmosphereElement, atmosphere.getCelestialBody(), elementDto.getName());
//            consumer.accept(dto);
//        };
//        atmosphereElementDao.pro(dbObjConsumer);
//    }

    /****************************************************************************************************************/
    public void processAllAtmosphereElements(Consumer<AtmosphereElementDto> consumer) {
        Consumer<AtmosphereElement> dbObjConsumer = atmosphereElement -> {
            ElementDto elementDto = ElementService.getInstance().getElementById(atmosphereElement.getElementId());
            AtmosphereDto atmosphere = AtmosphereService.getInstance().getAtmosphereById(atmosphereElement.getAtmosphereId());
            AtmosphereElementDto dto = atmosphereElementToDto(atmosphereElement, atmosphere.getCelestialBody(), elementDto.getName());
            consumer.accept(dto);
        };
        atmosphereElementDao.processAllAtmosphereElements(dbObjConsumer);
    }



    public void processFilteredAtmosphereElements(Consumer<AtmosphereElementDto> consumer, AtmosphereElementFilter filter) {
        Consumer<AtmosphereElement> dbObjConsumer = atmosphereElement -> {
            ElementDto elementDto = ElementService.getInstance().getElementById(atmosphereElement.getElementId());
            AtmosphereDto atmosphere = AtmosphereService.getInstance().getAtmosphereById(atmosphereElement.getAtmosphereId());
            AtmosphereElementDto dto = atmosphereElementToDto(atmosphereElement, atmosphere.getCelestialBody(), elementDto.getName());
            consumer.accept(dto);
        };
        atmosphereElementDao.processFilteredAtmosphereElements(dbObjConsumer, filter);
    }

}
