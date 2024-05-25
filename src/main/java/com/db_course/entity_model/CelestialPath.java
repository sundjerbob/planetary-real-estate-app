package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CelestialPath {

    private int id;
    private int bodyAId;
    private int bodyBId;
    private BigDecimal distanceKm;
}
