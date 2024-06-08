package com.db_course.gui.interaction;

import com.db_course.dto.DepartureDto;
import com.db_course.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartureResultsForm extends JFrame {
    private JTable departureTable;
    private MainFrame mainFrame;
    private DepartureDto selectedDeparture;

    public DepartureResultsForm(MainFrame mainFrame, DepartureDto departureDto) {
        this.mainFrame = mainFrame;
        this.selectedDeparture = departureDto;

        setTitle("Select Departure");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        departureTable = new JTable(); // Define appropriate table model and add data here

        JScrollPane scrollPane = new JScrollPane(departureTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton selectButton = new JButton("Select Departure");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTicketSelectionForm();
            }
        });

        add(selectButton, BorderLayout.SOUTH);

        // Add departure data to table
        addDepartureData(departureDto);

        setVisible(true);
    }

    private void addDepartureData(DepartureDto departureDto) {
        // Add data to the departureTable based on the departureDto
    }

    private void openTicketSelectionForm() {
        new BuyTicketsForm(mainFrame);
        dispose();
    }
}
