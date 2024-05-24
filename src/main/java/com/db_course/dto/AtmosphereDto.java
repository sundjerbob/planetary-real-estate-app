package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AtmosphereDto {

    private int atmosphereId;
    private int celestialBodyId;
    private int atmosphereHeight;
}
