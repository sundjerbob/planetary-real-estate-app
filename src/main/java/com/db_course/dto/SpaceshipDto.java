package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class SpaceshipDto {

    private int spaceshipId;
    private String name;
    private String model;
    private int passengerCapacity;
    private BigDecimal fuelCapacity;
    private BigDecimal maxTravelRange;
    private BigDecimal travelingSpeed;
    private String manufacturer;

}
