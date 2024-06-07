package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Atmosphere {

    private int id;
    private int celestialBodyId;
    private BigDecimal maxTemperature;
    private BigDecimal minTemperature;
    private BigDecimal atmosphereHeight;
    private BigDecimal amperePressure;
}
