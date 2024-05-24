package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MissionDto {

    private int missionId;
    private String name;
    private LocalDate launchDate;
    private int celestialBodyId;
}
