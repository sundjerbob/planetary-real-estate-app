package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialBodyResidentDto {

    private int id;
    private int residentId;
    private String celestialBody;
    private LocalDate residentFrom;
    private LocalDate residentUntil;

}
