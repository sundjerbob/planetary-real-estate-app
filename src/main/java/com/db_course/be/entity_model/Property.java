package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Property {

    private int id;
    private int celestialBodyId;
    private Integer soldToUserId;
    private int propertyRegNb;
    private String address;
    private BigDecimal squareMeters;
    private String name;
    private String description;
    private BigDecimal price;
}
