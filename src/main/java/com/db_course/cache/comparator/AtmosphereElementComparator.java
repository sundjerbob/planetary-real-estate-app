package com.db_course.cache.comparator;

import com.db_course.dto.AtmosphereElementDto;

import java.util.Comparator;

public class AtmosphereElementComparator implements Comparator<AtmosphereElementDto> {
    @Override
    public int compare(AtmosphereElementDto o1, AtmosphereElementDto o2) {

        return Comparator.comparing(AtmosphereElementDto::getPercentage).compare(o1, o2);
    }
}
