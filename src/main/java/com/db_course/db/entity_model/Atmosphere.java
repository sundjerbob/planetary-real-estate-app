package com.db_course.db.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atmosphere {
    private int atmosphereId;
    private int celestialBodyId;
    private int atmosphereHeight;
}
