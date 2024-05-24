package com.db_course.app.data_cache;

import com.db_course.db.entity_model.Departure;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class DepartureCache {
    private final TreeSet<Departure> departures;

    public DepartureCache() {
        departures = new TreeSet<>(Comparator.comparing(Departure::getDepartureDate).thenComparing(Departure::getDepartureId));
    }

    public void addDeparture(Departure departure) {
        departures.add(departure);
    }

    public NavigableSet<Departure> getDeparturesBetween(LocalDateTime start, LocalDateTime end) {
        return departures.subSet(
                new Departure(0, start, 0),
                true,
                new Departure(Integer.MAX_VALUE, end, Integer.MAX_VALUE),
                true);
    }

    public NavigableSet<Departure> getDeparturesOn(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1L).minusNanos(1L);
        return getDeparturesBetween(startOfDay, endOfDay);
    }

    // other potential methods here e.g., removeDeparture(), getDepartures(), etc.
}