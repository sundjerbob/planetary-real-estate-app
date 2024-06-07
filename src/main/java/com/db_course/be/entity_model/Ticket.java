package com.db_course.be.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ticket {


    private int id;
    private int departureId;
    private int spaceshipId;
    private int roomId;
    private BigDecimal price;
    private boolean soled;
    private Integer passengerId;


}
