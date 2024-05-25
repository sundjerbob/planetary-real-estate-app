package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Mission {

    private int id;
    private String name;
    private LocalDate launchDate;
    private int celestialBodyId;
}
