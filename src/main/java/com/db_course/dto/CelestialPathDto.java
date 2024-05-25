package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialPathDto {

    private int id;
    private int bodyA_Id;
    private int bodyB_Id;
    private BigDecimal distance_km;

}
