package com.db_course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class TicketDto {


    private int id;
    private int departureId;
    private int spaceshipId;
    private SpaceshipRoomDto room;
    private BigDecimal price;
    private boolean soled;
    private Integer passengerId;


}
