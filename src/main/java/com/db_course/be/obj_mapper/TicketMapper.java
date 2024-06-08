package com.db_course.be.obj_mapper;

import com.db_course.be.entity_model.Ticket;
import com.db_course.dto.TicketDto;

public class TicketMapper {

    public static TicketDto ticketToDto(Ticket ticket, String spaceship, String roomNb, String passenger) {

        return new TicketDto(
                ticket.getId(),
                ticket.getDepartureId(),
                spaceship,
                roomNb,
                ticket.getPrice(),
                passenger
        );
    }


}
