package com.db_course.service;

import com.db_course.dao.ResidentDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.ResidentDto;
import com.db_course.entity_model.Resident;

import java.util.function.Consumer;

import static com.db_course.obj_mapper.ResidentMapper.residentToDto;

public class ResidentService {


    private static volatile ResidentService instance;
    private static final Object mutex = new Object();
    private final ResidentDao residentDao;


    private ResidentService() {
        this.residentDao = new ResidentDao(DB_Client.getInstance().getConnection());
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
        residentDao.processAllResident(dbObjConsumer);
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