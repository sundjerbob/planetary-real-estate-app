package com.db_course.gui.interaction;

import com.db_course.be.service.TicketService;
import com.db_course.dto.TicketDto;
import com.db_course.dto.UserDto;

import javax.swing.*;
import java.awt.*;

public class UserTicketsForm extends JDialog {

    private UserDto user;
    private JList<TicketDto> ticketsList;
    private DefaultListModel<TicketDto> ticketsListModel;

    public UserTicketsForm(Frame owner, UserDto user) {
        super(owner, "My Tickets", true);
        this.user = user;
        initializeComponents();
        layoutComponents();
        fetchUserTickets();
    }

    private void initializeComponents() {
        ticketsListModel = new DefaultListModel<>();
        ticketsList = new JList<>(ticketsListModel);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new JScrollPane(ticketsList), BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(getOwner());
    }

    private void fetchUserTickets() {
        TicketService.getInstance().processTicketsBoughtByUser(user.getId(), this::populateTicketsList);
    }

    private void populateTicketsList(TicketDto ticket) {
        ticketsListModel.addElement(ticket);
    }
}
