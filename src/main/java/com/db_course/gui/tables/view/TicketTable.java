package com.db_course.gui.tables.view;

import com.db_course.dto.TicketDto;
import com.db_course.gui.tables.model.TicketTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicketTable extends JTable {
    private TicketDto selectedTicket;

    public TicketTable(TicketTableModel model) {
        super(model);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    selectedTicket = model.getTicketAt(selectedRow);
                }
            }
        });
    }

    public TicketDto getSelectedTicket() {
        return selectedTicket;
    }
}
