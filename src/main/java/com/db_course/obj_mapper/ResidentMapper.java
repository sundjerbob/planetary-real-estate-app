package com.db_course.obj_mapper;

import com.db_course.dto.ResidentDto;
import com.db_course.entity_model.Resident;

public class ResidentMapper {


    public static ResidentDto residentToDto(Resident resident) {


        return new ResidentDto(
                resident.getId(),
                resident.getFullName(),
                resident.getGender() == Resident.Gender.F ? "Female" : "Male",
                resident.getBirthDate(),
                resident.getDeathDate()
        );
    }
}
