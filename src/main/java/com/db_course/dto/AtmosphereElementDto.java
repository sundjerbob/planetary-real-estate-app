package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AtmosphereElementDto {

    private int atmosphereId;
    private int elementId;
    private double percentage;

}
