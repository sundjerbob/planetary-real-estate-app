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
    private int exploredBodyId;
    private int spaceshipId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean completed;
}
