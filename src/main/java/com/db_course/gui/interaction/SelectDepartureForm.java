package com.db_course.gui.interaction;

import com.db_course.be.filter.defs.FilterOperation;
import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.DepartureDto;
import com.db_course.be.service.CelestialBodyService;
import com.db_course.be.service.DepartureService;
import com.db_course.be.filter.entity_filters.impl.DepartureFilter;
import com.db_course.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SelectDepartureForm extends JFrame {
    private JComboBox<String> originComboBox;
    private JComboBox<String> destinationComboBox;
    private JTextField departureDateField;
    private MainFrame mainFrame;

    public SelectDepartureForm(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setTitle("Select Departure");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Origin:"));
        originComboBox = new JComboBox<>();
        panel.add(originComboBox);

        panel.add(new JLabel("Destination:"));
        destinationComboBox = new JComboBox<>();
        panel.add(destinationComboBox);

        panel.add(new JLabel("Departure Date:"));
        departureDateField = new JTextField();
        panel.add(departureDateField);

        JButton searchButton = new JButton("Search Departures");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDepartures();
            }
        });

        panel.add(searchButton);

        add(panel);

        loadCelestialBodies();

        setVisible(true);
    }

    private void loadCelestialBodies() {
        CelestialBodyService.getInstance().processAllCelestialBodies(celestialBody -> {
            originComboBox.addItem(celestialBody.getName());
            destinationComboBox.addItem(celestialBody.getName());
        });
    }

    private void searchDepartures() {
        String origin = (String) originComboBox.getSelectedItem();
        String destination = (String) destinationComboBox.getSelectedItem();
        String date = departureDateField.getText();

        DepartureFilter filter = new DepartureFilter();
        filter.addFilter(DepartureFilter.CELESTIAL_ORIGIN, FilterOperation.EQUAL, origin);
        filter.addFilter(DepartureFilter.CELESTIAL_DESTINATION, FilterOperation.EQUAL, destination);
        filter.addFilter(DepartureFilter.DEPARTURE_DATETIME, FilterOperation.EQUAL, LocalDateTime.parse(date));

        DepartureService.getInstance().processFilteredDepartures(departure -> {
            new DepartureResultsForm(mainFrame, departure);
        }, filter);

        dispose();
    }
}
