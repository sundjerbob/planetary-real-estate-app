package com.db_course.be.service;

import com.db_course.be.dao.ResidentDao;
import com.db_course.be.entity_model.Resident;
import com.db_course.be.filter.entity_filters.impl.ResidentFilter;
import com.db_course.dto.ResidentDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.ResidentMapper.residentToDto;


public class ResidentService {


    private static volatile ResidentService instance;
    private static final Object mutex = new Object();
    private final ResidentDao residentDao;


    private ResidentService() {
        this.residentDao = new ResidentDao();
    }


    public static ResidentService getInstance() {

        if (instance == null) {

            synchronized (mutex) {
                if (instance == null)
                    instance = new ResidentService();
            }
        }

        return instance;
    }


    public void processAllResidents(Consumer<ResidentDto> consumer) {

        Consumer<Resident> dbObjConsumer =
                resident -> {
                    ResidentDto residentDto = residentToDto(resident);
                    consumer.accept(residentDto);
                };
        residentDao.processAllResidents(dbObjConsumer);
    }


    public void processFilteredResidents(Consumer<ResidentDto> consumer, ResidentFilter filter) {

        Consumer<Resident> dbObjConsumer =
                resident -> {
                    ResidentDto residentDto = residentToDto(resident);
                    consumer.accept(residentDto);
                };
        residentDao.processFilteredResidents(dbObjConsumer, filter);
    }


    public void processResidentsWhoDiedBetweenAge20And40(int celestialBodyId, Consumer<ResidentDto> consumer) {

        Consumer<Resident> dbObjConsumer =
                resident -> {
                    ResidentDto residentDto = residentToDto(resident);
                    consumer.accept(residentDto);
                };

        residentDao.processResidentsWhoDiedBetweenAge20And40(celestialBodyId, dbObjConsumer);
    }


}
