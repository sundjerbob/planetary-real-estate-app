package com.db_course.be.service;

import com.db_course.be.dao.TicketDao;
import com.db_course.be.filter.entity_filters.impl.TicketFilter;
import com.db_course.dto.SpaceshipRoomDto;
import com.db_course.dto.TicketDto;

import java.util.function.Consumer;

import static com.db_course.be.obj_mapper.TicketMapper.ticketToDto;

public class TicketService {
    private static volatile TicketService instance;
    private static final Object mutex = new Object();
    private final TicketDao celestialPathDao;


    private TicketService() {
        celestialPathDao = new TicketDao();
    }


    public static TicketService getInstance() {
        if (instance == null) {
            synchronized (mutex) {
                if (instance == null) {
                    instance = new TicketService();
                }
            }
        }
        return instance;
    }


    public void processAllTickets(Consumer<TicketDto> consumer) {
        celestialPathDao.processAllTickets(
                ticket -> {

                    String bookedBy = ticket.getPassengerId() == null ?
                            "None"
                            :
                            UserService.getInstance().getUserById(ticket.getPassengerId()).getUsername();
                    SpaceshipRoomDto room = SpaceshipRoomService.getInstance().getRoomById(ticket.getRoomId());

                    TicketDto dto = ticketToDto(ticket, room.getSpaceship(), room.getRoomNumber(), bookedBy);
                    consumer.accept(dto);
                }
        );


    }

    public void processFilteredTickets(Consumer<TicketDto> consumer, TicketFilter filter) {
        celestialPathDao.processFilteredTickets(

                ticket -> {
                    String bookedBy = ticket.getPassengerId() == null ?
                            "None"
                            :
                            UserService.getInstance().getUserById(ticket.getPassengerId()).getUsername();

                    SpaceshipRoomDto room = SpaceshipRoomService.getInstance().getRoomById(ticket.getRoomId());

                    TicketDto dto = ticketToDto(ticket, room.getSpaceship(), room.getRoomNumber(), bookedBy);
                    consumer.accept(dto);
                },

                filter
        );


    }
}
