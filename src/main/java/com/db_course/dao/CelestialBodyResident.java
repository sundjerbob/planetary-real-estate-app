package com.db_course.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CelestialBodyResident {
    private int id;
    private int residentId;
    private int celestialBodyId;
    private LocalDate residentFrom;
    private LocalDate residentUntil;

}
