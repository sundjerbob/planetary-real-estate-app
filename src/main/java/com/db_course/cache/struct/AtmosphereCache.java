package com.db_course.cache.struct;

import com.db_course.cache.comparator.AtmosphereComparator;
import com.db_course.dto.AtmosphereDto;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class AtmosphereCache {


    private final TreeSet<AtmosphereDto> cache;
    private List<AtmosphereDto> showing;


    public AtmosphereCache() {
        cache = new TreeSet<>(new AtmosphereComparator());
    }

    public void add(AtmosphereDto atmosphereDto) {
        cache.add(atmosphereDto);
    }


    public void add(Collection<? extends AtmosphereDto> collection) {
        cache.addAll(collection);
    }




}
