package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidentDto {

    private int id;
    private String fullName;
    private String gender;
    private LocalDate birthDate;
    private LocalDate deathDate;

}
