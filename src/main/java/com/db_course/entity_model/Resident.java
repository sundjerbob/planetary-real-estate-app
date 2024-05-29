package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    private int id;
    private String fullName;
    private String gender;
    private LocalDate birthDate;
    private LocalDate deathDate;

}
