package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CelestialBodyResident {

    private int id;
    private int celestialBodyId;
    private int residentId;
    private LocalDate residentFrom;
    private LocalDate residentUntil;

}
