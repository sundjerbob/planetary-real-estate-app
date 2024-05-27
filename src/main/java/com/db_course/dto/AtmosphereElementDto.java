package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AtmosphereElementDto {

    private int atmosphereId;
    private ElementDto elementDto;
    private BigDecimal percentage;
}
