package com.db_course.ui_test.test1;


import com.db_course.entity_model.Mission;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;

public class MissionPanel extends JPanel {
    private JTable missionTable;
    private MissionTableModel tableModel;
    private List<Mission> missions;

    public MissionPanel() {
        setLayout(new BorderLayout());

        missions = new ArrayList<>();
        tableModel = new MissionTableModel(missions);
        missionTable = new JTable(tableModel);

        // Create a filter panel
        DynamicFilterPanel filterPanel = new DynamicFilterPanel();
        JButton filterButton = new JButton("Apply Filters");
        filterButton.addActionListener(e -> applyFilters(filterPanel));

        JPanel filterPanelWrapper = new JPanel(new BorderLayout());
        filterPanelWrapper.add(filterPanel, BorderLayout.CENTER);
        filterPanelWrapper.add(filterButton, BorderLayout.SOUTH);

        add(filterPanelWrapper, BorderLayout.NORTH);
        add(new JScrollPane(missionTable), BorderLayout.CENTER);

        loadMissions();
    }

    private void loadMissions() {
        NavigableSet<Mission> cachedMissions = DataCache.getInstance().getMissionCache().getMissionsBetween(LocalDate.now().minusYears(1), LocalDate.now().plusYears(1));
        missions.addAll(cachedMissions);
        tableModel.fireTableDataChanged();
    }

    private void applyFilters(DynamicFilterPanel filterPanel) {
        List<Mission> filteredMissions = new ArrayList<>();
        for (Mission mission : missions) {
            if (filterPanel.applyFilters(mission)) {
                filteredMissions.add(mission);
            }
        }
        missions.clear();
        missions.addAll(filteredMissions);
        tableModel.fireTableDataChanged();
    }
}
