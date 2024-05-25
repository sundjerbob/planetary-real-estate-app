package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Atmosphere {

    private int id;
    private int celestialBodyId;
    private int atmosphereHeight;

}
