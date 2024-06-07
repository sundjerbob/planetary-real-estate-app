package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AtmosphereDto {

    private int id;
    private BigDecimal maxTemperature;
    private BigDecimal minTemperature;
    private BigDecimal atmosphereHeight;
    private BigDecimal amperePressure;
    private String celestialBody;
}
