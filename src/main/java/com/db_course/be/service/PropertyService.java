package com.db_course.be.service;

import com.db_course.be.dao.PropertyDao;
import com.db_course.be.filter.entity_filters.impl.PropertyFilter;
import com.db_course.dto.PropertyDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.PropertyMapper.propertyToDto;

public class PropertyService {

    private static volatile PropertyService instance;
    private static final Object mutex = new Object();
    private final PropertyDao propertyDao;


    private PropertyService() {
        propertyDao = new PropertyDao();

    }

    public static PropertyService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new PropertyService();
                }
            }
        }
        return instance;
    }


    public void processPropertiesOwnedByUser(int userId, Consumer<PropertyDto> consumer) {

        propertyDao.processPropertiesOwnedByUserId(
                userId,
                property -> {
                    String cbName = CelestialBodyService.getInstance().getCelestialBodyById(property.getCelestialBodyId()).getName();
                    String soldTo = property.getSoldToUserId() == null ?
                            "None" : UserService.getInstance().getUserById(property.getSoldToUserId()).getUsername();

                    consumer.accept(propertyToDto(property, cbName, soldTo));

                }
        );


    }


    public void buyProperty(int userId, int propertyId) {

        propertyDao.buyProperty(userId, propertyId);
    }

    public void processAvailablePropertiesByCelestialBodyId(int celestialBodyId, Consumer<PropertyDto> consumer) {

        propertyDao.processAvailablePropertiesByCelestialBodyId(
                celestialBodyId,
                property -> {
                    String cbName = CelestialBodyService.getInstance().getCelestialBodyById(property.getCelestialBodyId()).getName();
                    String soldTo = property.getSoldToUserId() == null ?
                            "None" : UserService.getInstance().getUserById(property.getSoldToUserId()).getUsername();

                    consumer.accept(propertyToDto(property, cbName, soldTo));

                }
        );

    }


    public void processAllProperties(Consumer<PropertyDto> consumer) {

        propertyDao.processAllProperties(
                property -> {
                    String cbName = CelestialBodyService.getInstance().getCelestialBodyById(property.getCelestialBodyId()).getName();
                    String soldTo = property.getSoldToUserId() == null ?
                            "None" : UserService.getInstance().getUserById(property.getSoldToUserId()).getUsername();

                    consumer.accept(propertyToDto(property, cbName, soldTo));

                }
        );

    }


    public void processFilteredProperties(Consumer<PropertyDto> consumer, PropertyFilter filter) {

        propertyDao.processFilteredProperties(
                property -> {
                    String cbName = CelestialBodyService.getInstance().getCelestialBodyById(property.getCelestialBodyId()).getName();
                    String soldTo = property.getSoldToUserId() == null ?
                            "None" : UserService.getInstance().getUserById(property.getSoldToUserId()).getUsername();

                    consumer.accept(propertyToDto(property, cbName, soldTo));
                },
                filter
        );
    }


}
