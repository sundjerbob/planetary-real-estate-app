package com.db_course.app.ui.window;
import com.db_course.app.ui.filter_panels.ResidentFilterPanel;
import com.db_course.app.ui.tables.model.ResidentTableModel;
import com.db_course.app.ui.tables.view.ResidentTable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MainFrame extends JFrame {

    private ResidentTable residentTable;
    private ResidentTableModel residentTableModel;
    private ResidentFilterPanel residentFilterPanel;

    public MainFrame() {
        setTitle("Person Table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        residentTableModel = new ResidentTableModel();
        residentTable = new ResidentTable(residentTableModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(residentTable), BorderLayout.CENTER);

        // Create filter panel for person table
        residentFilterPanel = new ResidentFilterPanel(
                new String[]{"Full Name", "Gender"},
                new String[]{"Birth Date", "Death Date"},
                e -> applyPersonFilter(),
                e -> clearPersonFilter()
        );

        add(residentFilterPanel, BorderLayout.NORTH);
    }

    private void applyPersonFilter() {
        String query = residentFilterPanel.getFilterText();
        int stringColumn = residentFilterPanel.getSelectedColumnIndex();
        residentTableModel.filterByString(query, stringColumn);

        LocalDate startDate = residentFilterPanel.getStartDate();
        LocalDate endDate = residentFilterPanel.getEndDate();
        int dateColumn = residentFilterPanel.getSelectedDateColumnIndex() + 2; // Birth Date and Death Date are at index 2 and 3
        residentTableModel.filterByDate(startDate, endDate, dateColumn);
    }

    private void clearPersonFilter() {
        residentFilterPanel.clearFilterText();
        residentTableModel.filterByString("", 0);
        residentTableModel.filterByDate(null, null, 2);
    }


}
