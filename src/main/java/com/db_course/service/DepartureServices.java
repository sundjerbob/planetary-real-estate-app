package com.db_course.service;

import com.db_course.dao.DepartureDao;
import com.db_course.db_config.DB_Client;
import com.db_course.dto.CelestialPathDto;
import com.db_course.dto.DepartureDto;
import com.db_course.dto.SpaceshipDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Consumer;

import static com.db_course.obj_mapper.DepartureMapper.departureToDto;

public class DepartureServices {

    private static volatile DepartureServices instance;
    private static final Object mutex = new Object();
    private final DepartureDao departureDao;


    private DepartureServices() {
        departureDao = new DepartureDao();
    }

    /******************************************************************************************************************/
    public static DepartureServices getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new DepartureServices();
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
                    CelestialPathDto celestialPath = CelestialPathService.getInstance().getCelestialPathByBodyIds(
                            departure.getCelestialBodyFromId(),
                            departure.getCelestialBodyToId()
                    );
                    long travelDurationDays = calculateTravelDurationInDays(celestialPath.getDistance_km(), spaceship.getTravelingSpeed());
                    DepartureDto departureDto = departureToDto(departure, spaceship, celestialPath, travelDurationDays);
                    consumer.accept(departureDto);
                }
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
