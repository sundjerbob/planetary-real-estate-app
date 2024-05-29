package com.db_course.obj_mapper;

import com.db_course.dto.SpaceshipRoomDto;
import com.db_course.dto.TicketDto;
import com.db_course.entity_model.Ticket;

public class TicketMapper {

    public static TicketDto toTicketDto(Ticket ticket, SpaceshipRoomDto spaceshipRoom) {

        return new TicketDto(
                ticket.getId(),
                ticket.getDepartureId(),
                ticket.getSpaceshipId(),
                spaceshipRoom,
                ticket.getPrice(),
                ticket.isSoled(),
                ticket.getPassengerId()
        );
    }


}
