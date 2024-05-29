package com.db_course.ui_test.test1;

import com.db_course.cache.struct.DepartureCache;
import com.db_course.cache.struct.MissionCache;
import lombok.Getter;

@Getter
public class DataCache {
    private static DataCache instance;
    private final DepartureCache departureCache;
    private final MissionCache missionCache;

    private DataCache() {
        departureCache = new DepartureCache();
        missionCache = new MissionCache();
    }

    public static DataCache getInstance() {
        if (instance == null) {
            instance = new DataCache();
        }
        return instance;
    }

}
