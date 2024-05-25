package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PropertyDto {

    private int propertyId;
    private String name;
    private String description;
    private double price;
    private int celestialBodyId;

}