package com.db_course.ui_test.test1;

import com.db_course.dto.DepartureDto;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;

public class DeparturePanel extends JPanel {
    private final DepartureTableModel tableModel;
    private final List<DepartureDto> departures;

    public DeparturePanel() {
        setLayout(new BorderLayout());

        departures = new ArrayList<>();
        tableModel = new DepartureTableModel(departures);
        JTable departureTable = new JTable(tableModel);

        // Create a filter panel
        DynamicFilterPanel filterPanel = new DynamicFilterPanel();
        JButton filterButton = new JButton("Apply Filters");
        filterButton.addActionListener(e -> applyFilters(filterPanel));

        JPanel filterPanelWrapper = new JPanel(new BorderLayout());
        filterPanelWrapper.add(filterPanel, BorderLayout.CENTER);
        filterPanelWrapper.add(filterButton, BorderLayout.SOUTH);

        add(filterPanelWrapper, BorderLayout.NORTH);
        add(new JScrollPane(departureTable), BorderLayout.CENTER);

        loadDepartures();
    }

    private void loadDepartures() {
        NavigableSet<DepartureDto> cachedDepartures = DataCache.getInstance().getDepartureCache().getDeparturesBetween(LocalDateTime.now().minusYears(1), LocalDateTime.now().plusYears(1));
        departures.addAll(cachedDepartures);
        tableModel.fireTableDataChanged();
    }

    private void applyFilters(DynamicFilterPanel filterPanel) {
        List<DepartureDto> filteredDepartures = new ArrayList<>();
        for (DepartureDto departure : departures) {
            if (filterPanel.applyFilters(departure)) {
                filteredDepartures.add(departure);
            }
        }
        departures.clear();
        departures.addAll(filteredDepartures);
        tableModel.fireTableDataChanged();
    }
}
