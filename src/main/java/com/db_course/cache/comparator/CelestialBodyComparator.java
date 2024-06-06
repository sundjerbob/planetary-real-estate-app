package com.db_course.cache.comparator;

import com.db_course.dto.CelestialBodyDto;

import java.util.Comparator;

public class CelestialBodyComparator implements Comparator<CelestialBodyDto> {
    @Override
    public int compare(CelestialBodyDto o1, CelestialBodyDto o2) {

        return
                Comparator.comparing(CelestialBodyDto::getSurfaceArea).thenComparing(CelestialBodyDto::getName).compare(o1, o2);
    }
}
