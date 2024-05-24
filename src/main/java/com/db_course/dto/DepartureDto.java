package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DepartureDto {

    private int departureId;
    private LocalDateTime departureDate;
    private int passengerId;
}
