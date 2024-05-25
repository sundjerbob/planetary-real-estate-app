package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Property {
    private int propertyId;
    private String name;
    private String description;
    private double price;
    private int celestialBodyId;
}
