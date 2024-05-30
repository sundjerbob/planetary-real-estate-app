package com.db_course.gui.filter_panels;

import com.db_course.gui.filter_panels.datatype_filter.DateFilterPanel;
import com.db_course.gui.filter_panels.datatype_filter.StringFilterPanel;

import javax.swing.*;
import java.awt.*;

public class ResidentFilterPanel extends JPanel {

    private StringFilterPanel nameFilterPanel;
    private DateFilterPanel birthDateFilterPanel;
    private DateFilterPanel deathDateFilterPanel;
    private JButton filterButton;
    private JButton clearFilterButton;

    public ResidentFilterPanel() {
        setLayout(new FlowLayout());

        nameFilterPanel = new StringFilterPanel();
        birthDateFilterPanel = new DateFilterPanel();
        deathDateFilterPanel = new DateFilterPanel();

        filterButton = new JButton("Filter");
        clearFilterButton = new JButton("Clear Filter");

        add(new JLabel("Name Filter:"));
        add(nameFilterPanel);
        add(new JLabel("Birth Date Filter:"));
        add(birthDateFilterPanel);
        add(new JLabel("Death Date Filter:"));
        add(deathDateFilterPanel);
        add(filterButton);
        add(clearFilterButton);
    }

    public StringFilterPanel getNameFilterPanel() {
        return nameFilterPanel;
    }

    public DateFilterPanel getBirthDateFilterPanel() {
        return birthDateFilterPanel;
    }

    public DateFilterPanel getDeathDateFilterPanel() {
        return deathDateFilterPanel;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getClearFilterButton() {
        return clearFilterButton;
    }
}
