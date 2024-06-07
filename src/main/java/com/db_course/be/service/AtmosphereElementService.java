package com.db_course.be.service;

import com.db_course.be.dao.AtmosphereElementDao;
import com.db_course.dto.AtmosphereElementDto;
import com.db_course.dto.ElementDto;
import com.db_course.be.entity_model.AtmosphereElement;

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


    /****************************************************************************************************************/
    public void processAtmosphereElementByAtmosphereId(int atmosphereId, Consumer<AtmosphereElementDto> consumer) {
        Consumer<AtmosphereElement> dbObjConsumer = atmosphereElement -> {

            ElementDto elementDto = ElementService.getInstance().getElementById(atmosphereElement.getElementId());
            atmosphereElementToDto(atmosphereElement, elementDto);
        };
        atmosphereElementDao.processAllAtmosphereElements(dbObjConsumer);
    }

}
