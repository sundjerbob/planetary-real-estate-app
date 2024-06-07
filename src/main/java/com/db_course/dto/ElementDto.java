package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ElementDto {

    private int id;
    private String name;
    private String description;
    private double minPercentage;
    private double maxPercentage;
    private boolean radioactive;
    private boolean inert;

}
