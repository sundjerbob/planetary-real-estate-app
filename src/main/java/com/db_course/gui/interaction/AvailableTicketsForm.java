package com.db_course.gui.interaction;

import com.db_course.be.filter.defs.FilterOperation;
import com.db_course.be.filter.entity_filters.impl.TicketFilter;
import com.db_course.be.service.TicketService;
import com.db_course.dto.DepartureDto;
import com.db_course.dto.TicketDto;
import com.db_course.dto.UserDto;
import com.db_course.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class AvailableTicketsForm extends JDialog {

    private DepartureDto departure;
    private JList<TicketDto> ticketsList;
    private DefaultListModel<TicketDto> ticketsListModel;
    private JButton buyTicketButton;

    public AvailableTicketsForm(Frame owner, DepartureDto departure) {
        super(owner, "Available Tickets", true);
        this.departure = departure;
        initializeComponents();
        layoutComponents();
        fetchAvailableTickets();
        initializeBuyTicketButton();
    }

    private void initializeComponents() {
        ticketsListModel = new DefaultListModel<>();
        ticketsList = new JList<>(ticketsListModel);
        buyTicketButton = new JButton("Buy Ticket");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new JScrollPane(ticketsList), BorderLayout.CENTER);
        add(buyTicketButton, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(getOwner());
    }

    private void fetchAvailableTickets() {
        TicketFilter filter = new TicketFilter();
        filter.addFilter(TicketFilter.DEPARTURE_ID, FilterOperation.EQUAL, departure.getId());
        filter.addFilter(TicketFilter.SOLD, FilterOperation.EQUAL, false);

        TicketService.getInstance().processFilteredTickets(this::populateTicketsList, filter);
    }

    private void populateTicketsList(TicketDto ticket) {
        ticketsListModel.addElement(ticket);
    }

    private void initializeBuyTicketButton() {
        buyTicketButton.addActionListener(e -> buySelectedTicket());
    }

    private void buySelectedTicket() {
        TicketDto selectedTicket = ticketsList.getSelectedValue();
        if (selectedTicket != null) {
            MainFrame mainFrame = (MainFrame) getOwner();
            UserDto currentUser = mainFrame.getCurrentUser();
            TicketService.getInstance().buyTicket(selectedTicket.getId(), currentUser.getId());
            JOptionPane.showMessageDialog(this, "Ticket purchased successfully!");
            fetchAvailableTickets(); // Refresh the ticket list
        } else {
            JOptionPane.showMessageDialog(this, "Please select a ticket first.");
        }
    }
}
