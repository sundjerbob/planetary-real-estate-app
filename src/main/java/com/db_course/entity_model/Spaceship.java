package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Spaceship {

    private int spaceshipId;
    private String name;
    private String model;
    private int passengerCapacity;
    private BigDecimal fuelCapacity;
    private BigDecimal maxTravelRange;
    private BigDecimal travelingSpeed;
    private String manufacturer;

}
