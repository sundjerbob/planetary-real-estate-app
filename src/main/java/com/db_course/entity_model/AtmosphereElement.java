package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AtmosphereElement {

    private int atmosphereId;
    private int elementId;
    private double percentage;

}
