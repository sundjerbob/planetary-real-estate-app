package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Spaceship {

    private int id;
    private String name;
    private String model;
    private int passengerCapacity;
    private BigDecimal fuelCapacity;
    private BigDecimal maxTravelRange;
    private BigDecimal travelingSpeed;
    private String manufacturer;

}
