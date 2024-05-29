package com.db_course.service;

import com.db_course.dao.AtmosphereElementDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.AtmosphereElementDto;
import com.db_course.dto.ElementDto;
import com.db_course.entity_model.AtmosphereElement;

import java.util.function.Consumer;

import static com.db_course.obj_mapper.AtmosphereElementMapper.atmosphereElementToDto;

public class AtmosphereElementService {


    private static volatile AtmosphereElementService instance;
    private static final Object mutex = new Object();
    private final AtmosphereElementDao atmosphereElementDao;


    private AtmosphereElementService() {
        atmosphereElementDao = new AtmosphereElementDao(
                DB_Client.getInstance().getConnection()
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


    /****************************************************************************************************************/
    public void processAtmosphereElementByAtmosphereId(int atmosphereId, Consumer<AtmosphereElementDto> consumer) {
        Consumer<AtmosphereElement> dbObjConsumer = atmosphereElement -> {

            ElementDto elementDto = ElementService.getInstance().getElementById(atmosphereElement.getElementId());
            atmosphereElementToDto(atmosphereElement, elementDto);
        };

    }

}
