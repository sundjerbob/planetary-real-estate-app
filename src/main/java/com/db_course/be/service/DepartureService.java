package com.db_course.be.service;

import com.db_course.be.dao.DepartureDao;
import com.db_course.be.filter.entity_filters.impl.DepartureFilter;
import com.db_course.dto.CelestialPathDto;
import com.db_course.dto.DepartureDto;
import com.db_course.dto.SpaceshipDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.DepartureMapper.departureToDto;

public class DepartureService {

    private static volatile DepartureService instance;
    private static final Object mutex = new Object();
    private final DepartureDao departureDao;


    private DepartureService() {
        departureDao = new DepartureDao();
    }

    /******************************************************************************************************************/
    public static DepartureService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new DepartureService();
                }
            }
        }
        return instance;
    }


    /******************************************************************************************************************/
    public void processAllDepartures(Consumer<DepartureDto> consumer) {
        departureDao.processAllDepartures(
                departure -> {

                    SpaceshipDto spaceship = SpaceshipService.getInstance().getSpaceshipById(departure.getSpaceshipId());

                    System.out.println(departure.getCelestialBodyOriginId() + " " + departure.getCelestialBodyDestinationId());
                    CelestialPathDto celestialPath = CelestialPathService.getInstance().getCelestialPathByBodyIds(
                            departure.getCelestialBodyOriginId(),
                            departure.getCelestialBodyDestinationId()
                    );

                    long travelDurationDays = calculateTravelDurationInDays(celestialPath.getDistanceKm(), spaceship.getTravelingSpeed());

                    DepartureDto departureDto = departureToDto(
                            departure,
                            spaceship.getName(),
                            celestialPath.getBodyA(),
                            celestialPath.getBodyB(),
                            travelDurationDays);

                    consumer.accept(departureDto);
                }
        );
    }

    /******************************************************************************************************************/
    public void processFilteredDepartures(Consumer<DepartureDto> consumer, DepartureFilter filter) {
        departureDao.processFilteredDepartures(
                departure -> {

                    SpaceshipDto spaceship = SpaceshipService.getInstance().getSpaceshipById(departure.getSpaceshipId());
                    CelestialPathDto celestialPath = CelestialPathService.getInstance().getCelestialPathByBodyIds(
                            departure.getCelestialBodyOriginId(),
                            departure.getCelestialBodyDestinationId()
                    );

                    long travelDurationDays = calculateTravelDurationInDays(celestialPath.getDistanceKm(), spaceship.getTravelingSpeed());

                    DepartureDto departureDto = departureToDto(
                            departure,
                            spaceship.getName(),
                            celestialPath.getBodyA(),
                            celestialPath.getBodyB(),
                            travelDurationDays);

                    consumer.accept(departureDto);
                },
                filter
        );

    }


    /******************************************************************************************************************/
    private long calculateTravelDurationInDays(BigDecimal distanceKm, BigDecimal speedKmPerHr) {
        // Check if speed is zero to avoid division by zero
        if (speedKmPerHr.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Speed must be greater than zero");
        }

        // Calculate hours to travel the given distance
        BigDecimal travelDurationInHours = distanceKm.divide(speedKmPerHr, 2, RoundingMode.HALF_UP);

        // Convert hours to days
        BigDecimal travelDurationInDays = travelDurationInHours.divide(BigDecimal.valueOf(24), 0, RoundingMode.UP);

        // Return the duration in days as a long
        return travelDurationInDays.longValue();
    }
}
