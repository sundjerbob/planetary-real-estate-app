package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MissionDto {


    private int id;
    private String celestialBody;
    private String spaceship;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean completed;
}
