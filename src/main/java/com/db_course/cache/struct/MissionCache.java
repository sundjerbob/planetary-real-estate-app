package com.db_course.cache.struct;

import com.db_course.entity_model.Mission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MissionCache {
    private final TreeSet<Mission> missions;

    public MissionCache() {
        missions = new TreeSet<>(Comparator.comparing(Mission::getStartDate).thenComparing(Mission::getExploredBodyId));
    }


    public void addMission(Mission mission) {
        missions.add(mission);
    }


    public NavigableSet<Mission> getMissionsOn(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1L).minusNanos(1L);
        return getMissionsBetween(startOfDay.toLocalDate(), endOfDay.toLocalDate());
    }

    public NavigableSet<Mission> getMissionsBetween(LocalDate start, LocalDate end) {
        Mission lowerBound = new Mission();
        lowerBound.setExploredBodyId(0);
        lowerBound.setStartDate(start);


        Mission upperBound = new Mission();
        upperBound.setStartDate(end);
        upperBound.setExploredBodyId(Integer.MAX_VALUE);

        return missions.subSet(lowerBound, true, upperBound, true);
    }

    public NavigableSet<Mission> getMissionsBetweenForCelestialBody(LocalDate start, LocalDate end, int exploredBodyId) {
        Mission lowerBound = new Mission();
        lowerBound.setExploredBodyId(exploredBodyId);
        lowerBound.setStartDate(start);

        Mission upperBound = new Mission();
        upperBound.setStartDate(end);
        upperBound.setExploredBodyId(exploredBodyId);

        return missions.subSet(lowerBound, true, upperBound, true);
    }

    public NavigableSet<Mission> getMissionsOnForCelestialBody(LocalDate date, int celestialBodyId) {
        Mission lowerBound = new Mission();
        lowerBound.setExploredBodyId(celestialBodyId);
        lowerBound.setStartDate(date);

        Mission upperBound = new Mission();
        upperBound.setStartDate(date);
        upperBound.setExploredBodyId(celestialBodyId);

        return missions.subSet(lowerBound, true, upperBound, true);
    }

    // other potential methods here e.g., removeMission(), getMissions(), etc.
}