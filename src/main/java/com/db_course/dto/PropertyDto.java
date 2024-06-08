package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PropertyDto {

    private int id;
    private String celestialBody;
    private String soldToUser;
    private int propertyRegNb;
    private String address;
    private BigDecimal squareMeters;
    private String name;
    private String description;
    private BigDecimal price;

}
