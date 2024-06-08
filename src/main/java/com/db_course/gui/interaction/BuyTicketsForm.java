package com.db_course.gui.interaction;

import com.db_course.be.filter.defs.FilterOperation;
import com.db_course.be.filter.entity_filters.impl.DepartureFilter;
import com.db_course.be.service.CelestialBodyService;
import com.db_course.be.service.DepartureService;
import com.db_course.dto.CelestialBodyDto;
import com.db_course.dto.DepartureDto;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BuyTicketsForm extends JDialog {

    private JComboBox<String> originChooser;
    private JComboBox<String> destinationChooser;
    private JTextField departureDateField;
    private JButton searchButton;
    private JButton selectDepartureButton;
    private JList<DepartureDto> departuresList;
    private DefaultListModel<DepartureDto> departuresListModel;

    public BuyTicketsForm(Frame owner) {
        super(owner, "Select Departure", true);
        initializeComponents();
        layoutComponents();
        fetchCelestialBodies();
        initializeSearchButton();
        initializeSelectDepartureButton();
    }

    private void initializeComponents() {
        originChooser = new JComboBox<>();
        destinationChooser = new JComboBox<>();
        departureDateField = new JTextField(15);
        searchButton = new JButton("Search Departures");
        selectDepartureButton = new JButton("Select Departure");
        departuresListModel = new DefaultListModel<>();
        departuresList = new JList<>(departuresListModel);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2)); // Adjusted to 5 rows to include the Select button
        inputPanel.add(new JLabel("Origin:"));
        inputPanel.add(originChooser);
        inputPanel.add(new JLabel("Destination:"));
        inputPanel.add(destinationChooser);
        inputPanel.add(new JLabel("Departure Date:"));
        inputPanel.add(departureDateField);
        inputPanel.add(searchButton);
        inputPanel.add(selectDepartureButton); // Added the Select button

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(departuresList), BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(getOwner());
    }

    private void fetchCelestialBodies() {
        CelestialBodyService.getInstance().processAllCelestialBodies(this::populateCelestialBodyChoices);
    }

    private void populateCelestialBodyChoices(CelestialBodyDto body) {
        originChooser.addItem(body.getName());
        destinationChooser.addItem(body.getName());
    }

    private void initializeSearchButton() {
        searchButton.addActionListener(e -> searchDepartures());
    }

    private void searchDepartures() {
        departuresListModel.clear();

        String origin = (String) originChooser.getSelectedItem();
        String destination = (String) destinationChooser.getSelectedItem();
        String date = departureDateField.getText().trim();

        DepartureFilter filter = new DepartureFilter();
        filter.addFilter(DepartureFilter.CELESTIAL_ORIGIN, FilterOperation.EQUAL, origin);
        filter.addFilter(DepartureFilter.CELESTIAL_DESTINATION, FilterOperation.EQUAL, destination);

        if (!date.isEmpty()) {
            try {
                LocalDateTime intervalStart = LocalDateTime.parse(date + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME); // Adjust date format parsing as needed
                LocalDateTime intervalEnd = LocalDateTime.parse(date + "T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME); // Adjust date format parsing as needed
                filter.addFilter(DepartureFilter.DEPARTURE_DATETIME, FilterOperation.BETWEEN, intervalStart, intervalEnd);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Use yyyy-MM-ddTHH:mm:ss");
                return;
            }
        }

        DepartureService.getInstance().processFilteredDepartures(this::populateDeparturesList, filter);
    }

    private void populateDeparturesList(DepartureDto departure) {
        departuresListModel.addElement(departure);
    }

    private void initializeSelectDepartureButton() {
        selectDepartureButton.addActionListener(e -> selectDeparture());
    }

    private void selectDeparture() {
        DepartureDto selectedDeparture = departuresList.getSelectedValue();
        if (selectedDeparture != null) {
            showAvailableTicketsForm(selectedDeparture);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a departure first.");
        }
    }

    private void showAvailableTicketsForm(DepartureDto departure) {
        AvailableTicketsForm availableTicketsForm = new AvailableTicketsForm((Frame) getOwner(), departure);
        availableTicketsForm.setVisible(true);
    }
}
