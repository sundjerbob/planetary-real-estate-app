package com.db_course.db.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CelestialPathway {

    private int id;
    private int bodyAId;
    private int bodyBId;
    private double distanceKm;
}
