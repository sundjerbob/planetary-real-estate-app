package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Element {

    private int id;
    private String name;
    private String description;
    private double minPercentage;
    private double maxPercentage;
    private boolean radioactive;
    private boolean inert;

}
