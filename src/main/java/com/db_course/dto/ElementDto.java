package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ElementDto {

    private int elementId;
    private String name;
    private double minPercentage;
    private double maxPercentage;
}
