package com.db_course.cache.comparator;

import com.db_course.dto.AtmosphereDto;

import java.util.Comparator;

public class AtmosphereComparator implements Comparator<AtmosphereDto> {


    @Override
    public int compare(AtmosphereDto o1, AtmosphereDto o2) {


        return Comparator.comparing(AtmosphereDto::getAtmosphereId).compare(o1, o2);

    }
}
