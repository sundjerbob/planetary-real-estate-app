package com.db_course.cache;

import com.db_course.dto.DepartureDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DepartureCache {
    private final TreeSet<DepartureDto> departures;

    public DepartureCache() {
        departures = new TreeSet<>(Comparator.comparing(DepartureDto::getDepartureDate).thenComparing(DepartureDto::getId));
    }

    public void addDeparture(DepartureDto departure) {

        departures.add(departure);
    }

    public NavigableSet<DepartureDto> getDeparturesBetween(LocalDateTime start, LocalDateTime end) {

        DepartureDto departure = new DepartureDto(), departure1 = new DepartureDto();
        departure.setDepartureDate(start);
        departure.setId(0);
        departure1.setDepartureDate(end);
        departure1.setId(Integer.MAX_VALUE);

        return departures.subSet(
                departure,
                true,
                departure1,
                true);
    }

    public NavigableSet<DepartureDto> getDeparturesOn(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1L).minusNanos(1L);
        return getDeparturesBetween(startOfDay, endOfDay);
    }

    // other potential methods here e.g., removeDeparture(), getDepartures(), etc.
}