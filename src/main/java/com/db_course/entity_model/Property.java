package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Property {

    private int propertyId;
    private int celestialBodyId;
    private Integer soldToUserId;
    private int globalRegistryNb;
    private String address;
    private BigDecimal squareMeters;
    private String description;
    private BigDecimal price;
}
