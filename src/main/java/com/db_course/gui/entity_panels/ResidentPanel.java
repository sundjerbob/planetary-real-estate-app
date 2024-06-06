package com.db_course.gui.entity_panels;
import com.db_course.gui.filter_panels.ResidentFilterPanel;
import com.db_course.gui.tables.model.ResidentTableModel;
import com.db_course.gui.tables.view.ResidentTable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ResidentPanel extends JPanel {

    private ResidentTable residentTable;
    private ResidentTableModel residentTableModel;
    private ResidentFilterPanel residentFilterPanel;

    public ResidentPanel() {
        setLayout(new BorderLayout());

        residentTableModel = new ResidentTableModel();
        residentTable = new ResidentTable(residentTableModel);
        residentFilterPanel = new ResidentFilterPanel();

        add(new JScrollPane(residentTable), BorderLayout.CENTER);
        add(residentFilterPanel, BorderLayout.NORTH);

        residentFilterPanel.getFilterButton().addActionListener(e -> applyFilters());
        residentFilterPanel.getClearFilterButton().addActionListener(e -> clearFilters());
    }

    private void applyFilters() {

        // Full Name column index
        String nameQuery = residentFilterPanel.getNameFilterPanel().getValue();
        int nameColumn = 1;
        residentTableModel.filterByString(nameQuery, nameColumn);

        String dateStart = residentFilterPanel.getBirthDateFilterPanel().getStartDate();
        String dateEnd = residentFilterPanel.getBirthDateFilterPanel().getEndDate();

        LocalDate birthStartDate = LocalDate.parse(residentFilterPanel.getBirthDateFilterPanel().getStartDate());
        LocalDate birthEndDate = LocalDate.parse(residentFilterPanel.getBirthDateFilterPanel().getEndDate());
        int birthDateColumn = 3;
        residentTableModel.filterByDate(birthStartDate, birthEndDate, birthDateColumn);

        LocalDate deathStartDate = LocalDate.parse(residentFilterPanel.getDeathDateFilterPanel().getStartDate());
        LocalDate deathEndDate = LocalDate.parse(residentFilterPanel.getDeathDateFilterPanel().getEndDate());
        int deathDateColumn = 4; // Death Date column index
        residentTableModel.filterByDate(deathStartDate, deathEndDate, deathDateColumn);
    }


    private void clearFilters() {
        residentFilterPanel.getNameFilterPanel().setValue("");
        residentFilterPanel.getBirthDateFilterPanel().setStartDate("");
        residentFilterPanel.getBirthDateFilterPanel().setEndDate("");
        residentFilterPanel.getDeathDateFilterPanel().setStartDate("");
        residentFilterPanel.getDeathDateFilterPanel().setEndDate("");
        residentTableModel.filterByString("", 1);
        residentTableModel.filterByDate(null, null, 3);
        residentTableModel.filterByDate(null, null, 4);
    }
}
