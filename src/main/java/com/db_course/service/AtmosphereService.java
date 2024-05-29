package com.db_course.service;

import com.db_course.dao.AtmosphereDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.AtmosphereDto;
import com.db_course.entity_model.Atmosphere;

import static com.db_course.obj_mapper.AtmosphereMapper.atmosphereToDto;

public class AtmosphereService {


    private static volatile AtmosphereService instance;
    private static final Object mutex = new Object();
    private final AtmosphereDao atmosphereDao;


    private AtmosphereService() {
        atmosphereDao = new AtmosphereDao(
                DB_Client.getInstance().getConnection()
        );
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
        return atmosphereToDto(atmosphere);
    }


}
