package com.db_course.service;

import com.db_course.dao.ElementDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.ElementDto;
import com.db_course.entity_model.Element;

import java.util.function.Consumer;

import static com.db_course.dto_mapper.ElementMapper.elementToDto;

public class ElementService {

    private static volatile ElementService instance;
    private static final Object mutex = new Object();
    private final ElementDao elementDao;


    private ElementService() {
        elementDao = new ElementDao(
                DB_Client.getInstance().getConnection()
        );
    }

    /******************************************************************************************************************/
    public static ElementService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new ElementService();
                }
            }
        }
        return instance;
    }


    /******************************************************************************************************************/
    public ElementDto getElementById(int elementId) {
        Element element = elementDao.getElementById(elementId);
        return elementToDto(element);
    }


    /******************************************************************************************************************/
    public void processAllElements(Consumer<ElementDto> consumer) {
        elementDao.processAllElements(
                element -> {
                    consumer.accept(elementToDto(element));
                }
        );

    }
}
