package com.db_course.test_serv;

import com.db_course.dto.CelestialPathDto;
import com.db_course.entity_model.CelestialPath;
import com.db_course.service.CelestialPathService;

import java.util.function.Consumer;

public class CelestialPathTest {

    public static void main(String[] args) {
        CelestialPathService celestialPathService = CelestialPathService.getInstance();

        // Test processAllPaths method
        testProcessAllPaths(celestialPathService);

        // Test processPathsByBodyId method with a specific body ID
        testProcessPathsByBodyId(celestialPathService, 1); // Replace 1 with the desired celestial body ID
    }

    private static void testProcessAllPaths(CelestialPathService celestialPathService) {
        System.out.println("Testing processAllPaths method...");
        celestialPathService.processAllPaths(new Consumer<CelestialPath>() {
            @Override
            public void accept(CelestialPath celestialPath) {
                System.out.println(celestialPath.toString());
            }
        });
        System.out.println("Testing processAllPaths method completed.\n");
    }

    private static void testProcessPathsByBodyId(CelestialPathService celestialPathService, int bodyId) {
        System.out.println("Testing processPathsByBodyId method for body ID: " + bodyId + "...");
        celestialPathService.processPathsByBodyId(bodyId, new Consumer<CelestialPathDto>() {
            @Override
            public void accept(CelestialPathDto celestialPath) {
                System.out.println(celestialPath.toString());
            }
        });
        System.out.println("Testing processPathsByBodyId method completed for body ID: " + bodyId + ".\n");
    }
}
