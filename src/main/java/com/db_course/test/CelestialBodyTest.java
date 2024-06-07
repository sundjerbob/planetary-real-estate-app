package com.db_course.test;

import com.db_course.be.service.CelestialBodyService;
import com.db_course.dto.CelestialBodyDto;

import java.util.function.Consumer;

public class CelestialBodyTest {

    public static void main(String[] args) {
        CelestialBodyTest test = new CelestialBodyTest();
        test.testProcessAllCelestialBodies();
        test.testProcessCelestialBodiesByType();
    }

    public void testProcessAllCelestialBodies() {
        System.out.println("testProcessAllCelestialBodies");

        CelestialBodyService service = CelestialBodyService.getInstance();

        service.processAllCelestialBodies(new Consumer<CelestialBodyDto>() {
            @Override
            public void accept(CelestialBodyDto celestialBodyDto) {
                System.out.println(celestialBodyDto);
            }
        });
    }

    public void testProcessCelestialBodiesByType() {
        System.out.println("testProcessCelestialBodiesByType");
        CelestialBodyService service = CelestialBodyService.getInstance();

        service.processCelestialBodiesByTypeId(2, new Consumer<CelestialBodyDto>() {
            @Override
            public void accept(CelestialBodyDto celestialBodyDto) {
                System.out.println(celestialBodyDto);
            }
        });
    }
}