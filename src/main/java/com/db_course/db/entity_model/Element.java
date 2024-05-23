package com.db_course.db.entity_model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Element {
    private int elementId;
    private String name;
    private double minPercentage;
    private double maxPercentage;
}
