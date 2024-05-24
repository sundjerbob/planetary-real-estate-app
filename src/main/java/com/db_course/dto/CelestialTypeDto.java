package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CelestialTypeDto {

    private int celestialTypeId;
    private String name;
    private String description;

}
