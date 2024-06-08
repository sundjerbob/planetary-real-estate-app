package com.db_course.be.service;

import com.db_course.be.dao.ElementDao;
import com.db_course.be.entity_model.Element;
import com.db_course.be.filter.entity_filters.impl.ElementFilter;
import com.db_course.dto.ElementDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.ElementMapper.elementToDto;

public class ElementService {

    private static volatile ElementService instance;
    private static final Object mutex = new Object();
    private final ElementDao elementDao;


    private ElementService() {
        elementDao = new ElementDao();
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

    public void processFilteredElements(Consumer<ElementDto> consumer, ElementFilter filter) {
        elementDao.processFilteredElements(
                element -> {
                    consumer.accept(elementToDto(element));
                },
                filter
        );

    }
}
