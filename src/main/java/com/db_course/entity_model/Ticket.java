package com.db_course.entity_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ticket {

    private int ticketId;
    private int departureId;
    private int passengerId;
    private int roomId;
    private int spaceshipId;
    private BigDecimal price;

}
