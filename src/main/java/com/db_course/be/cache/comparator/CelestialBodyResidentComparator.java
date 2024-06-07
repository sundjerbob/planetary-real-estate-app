package com.db_course.be.cache.comparator;

import com.db_course.dto.CelestialBodyResidentDto;

import java.util.Comparator;

public class CelestialBodyResidentComparator implements Comparator<CelestialBodyResidentDto> {
    @Override
    public int compare(CelestialBodyResidentDto o1, CelestialBodyResidentDto o2) {
        return Comparator.comparing(CelestialBodyResidentDto::getCelestialBody).compare(o1, o2);
    }
}
