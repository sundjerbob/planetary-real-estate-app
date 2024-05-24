package com.db_course.app.data_cache;

import com.db_course.db.entity_model.Mission;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MissionCache {
    private final TreeSet<Mission> missions;

    public MissionCache() {
        missions = new TreeSet<>(Comparator.comparing(Mission::getLaunchDate).thenComparing(Mission::getCelestialBodyId));
    }

    public void addMission(Mission mission) {
        missions.add(mission);
    }

    public NavigableSet<Mission> getMissionsOn(LocalDate date) {
        return missions.subSet(
                new Mission(0, "", date, 0),
                true,
                new Mission(Integer.MAX_VALUE, "", date, Integer.MAX_VALUE),
                true);
    }

    public NavigableSet<Mission> getMissionsBetween(LocalDate start, LocalDate end) {
        return missions.subSet(
                new Mission(0, "", start, 0),
                true,
                new Mission(Integer.MAX_VALUE, "", end, Integer.MAX_VALUE),
                true);
    }

    public NavigableSet<Mission> getMissionsBetweenForCelestialBody(LocalDate start, LocalDate end, int celestialBodyId) {
        return getMissionsBetween(start, end).subSet(
                new Mission(0, "", start, celestialBodyId),
                true,
                new Mission(Integer.MAX_VALUE, "", end, celestialBodyId),
                true);
    }

    public NavigableSet<Mission> getMissionsOnForCelestialBody(LocalDate date, int celestialBodyId) {
        return getMissionsOn(date).subSet(
                new Mission(0, "", date, celestialBodyId),
                true,
                new Mission(Integer.MAX_VALUE, "", date, celestialBodyId),
                true);
    }

    // other potential methods here e.g., removeMission(), getMissions(), etc.
}