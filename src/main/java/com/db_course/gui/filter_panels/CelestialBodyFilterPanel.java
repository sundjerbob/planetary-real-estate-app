package com.db_course.gui.filter_panels;

import com.db_course.be.filter.defs.FilterOperation;
import com.db_course.be.filter.entity_filters.impl.CelestialBodyFilter;
import com.db_course.be.service.CelestialBodyService;
import com.db_course.gui.tables.model.CelestialBodyTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelestialBodyFilterPanel extends JPanel {
    private final CelestialBodyTableModel tableModel;
    private final JPanel filterContainer;
    private final JButton addFilterButton;
    private final JButton applyFilterButton;

    public CelestialBodyFilterPanel(CelestialBodyTableModel tableModel) {
        this.tableModel = tableModel;
        setLayout(new BorderLayout());

        filterContainer = new JPanel();
        filterContainer.setLayout(new BoxLayout(filterContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(filterContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addFilterButton = new JButton("Add Filter");
        addFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewFilter();
            }
        });

        applyFilterButton = new JButton("Apply Filters");
        applyFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addFilterButton);
        buttonPanel.add(applyFilterButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addNewFilter() {
        FilterComponent filterComponent = new FilterComponent();
        filterContainer.add(filterComponent);
        filterContainer.revalidate();
        filterContainer.repaint();
    }

    private void applyFilters() {
        CelestialBodyFilter filter = new CelestialBodyFilter();
        for (Component component : filterContainer.getComponents()) {
            if (component instanceof FilterComponent) {
                ((FilterComponent) component).applyFilter(filter);
            }
        }
        tableModel.clear();
        CelestialBodyService.getInstance().processFilteredCelestialBodies(tableModel::addCelestialBody, filter);
    }

    private class FilterComponent extends JPanel {
        private final JComboBox<String> columnComboBox;
        private final JComboBox<FilterOperation> operationComboBox;
        private final JPanel valuePanel;

        public FilterComponent() {
            setLayout(new BorderLayout());
            columnComboBox = new JComboBox<>(new String[]{
                    "ID", "Name", "Description", "Surface Pressure", "Surface Temperature Min",
                    "Surface Temperature Max", "Core Temperature", "Explored", "Radiation Level", "Has Water",
                    "Surface Area", "Mass", "Gravitational Field Height", "Moving Speed", "Rotation Speed",
                    "Celestial Body Type", "Rotates Around Body"
            });

            columnComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateOperationComboBox();
                }
            });

            operationComboBox = new JComboBox<>();
            operationComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateValuePanel();
                }
            });

            valuePanel = new JPanel();
            valuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            updateOperationComboBox();

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    filterContainer.remove(FilterComponent.this);
                    filterContainer.revalidate();
                    filterContainer.repaint();
                }
            });

            JPanel topPanel = new JPanel();
            topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            topPanel.add(columnComboBox);
            topPanel.add(operationComboBox);
            topPanel.add(removeButton);

            add(topPanel, BorderLayout.NORTH);
            add(valuePanel, BorderLayout.CENTER);
        }

        private void updateOperationComboBox() {
            operationComboBox.removeAllItems();
            valuePanel.removeAll();

            String selectedColumn = (String) columnComboBox.getSelectedItem();
            if (selectedColumn != null) {
                switch (selectedColumn) {
                    case "Surface Pressure":
                    case "Surface Temperature Min":
                    case "Surface Temperature Max":
                    case "Core Temperature":
                    case "Surface Area":
                    case "Mass":
                    case "Gravitational Field Height":
                    case "Moving Speed":
                    case "Rotation Speed":
                        for (FilterOperation operation : FilterOperation.values()) {
                            if (operation == FilterOperation.BETWEEN || operation == FilterOperation.EQUAL ||
                                    operation == FilterOperation.NOT_EQUAL || operation == FilterOperation.LESS_THAN ||
                                    operation == FilterOperation.LESS_THAN_OR_EQUAL || operation == FilterOperation.GREATER_THAN ||
                                    operation == FilterOperation.GREATER_THAN_OR_EQUAL) {
                                operationComboBox.addItem(operation);
                            }
                        }
                        break;
                    case "Name":
                    case "Description":
                    case "Radiation Level":
                    case "Celestial Body Type":
                    case "Rotates Around Body":
                        for (FilterOperation operation : FilterOperation.values()) {
                            if (operation == FilterOperation.CONTAINS || operation == FilterOperation.BEGINS_WITH ||
                                    operation == FilterOperation.EQUAL || operation == FilterOperation.NOT_EQUAL) {
                                operationComboBox.addItem(operation);
                            }
                        }
                        break;
                    case "Explored":
                    case "Has Water":
                        for (FilterOperation operation : FilterOperation.values()) {
                            if (operation == FilterOperation.IS_TRUE || operation == FilterOperation.IS_FALSE) {
                                operationComboBox.addItem(operation);
                            }
                        }
                        break;
                    default:
                        for (FilterOperation operation : FilterOperation.values()) {
                            operationComboBox.addItem(operation);
                        }
                        break;
                }
            }

            updateValuePanel();
        }

        private void updateValuePanel() {
            valuePanel.removeAll();

            FilterOperation selectedOperation = (FilterOperation) operationComboBox.getSelectedItem();
            if (selectedOperation != null) {
                switch (selectedOperation) {
                    case BETWEEN:
                        valuePanel.add(new JLabel("From:"));
                        valuePanel.add(new JTextField(10));
                        valuePanel.add(new JLabel("To:"));
                        valuePanel.add(new JTextField(10));
                        break;
                    case IS_NULL:
                    case IS_NOT_NULL:
                    case IS_TRUE:
                    case IS_FALSE:
                        // No additional value input needed
                        break;
                    default:
                        valuePanel.add(new JTextField(10));
                        break;
                }
            }

            valuePanel.revalidate();
            valuePanel.repaint();
        }

        private void applyFilter(CelestialBodyFilter filter) {
            String selectedColumn = (String) columnComboBox.getSelectedItem();
            FilterOperation selectedOperation = (FilterOperation) operationComboBox.getSelectedItem();
            if (selectedColumn != null && selectedOperation != null) {
                int columnIndex = getColumnIndex(selectedColumn);
                Object value = getValueFromPanel();
                filter.setFilter(columnIndex, selectedOperation, value);
            }
        }

        private int getColumnIndex(String columnName) {
            switch (columnName) {
                case "ID": return CelestialBodyFilter.ID;
                case "Name": return CelestialBodyFilter.NAME;
                case "Description": return CelestialBodyFilter.DESCRIPTION;
                case "Surface Pressure": return CelestialBodyFilter.SURFACE_PRESSURE;
                case "Surface Temperature Min": return CelestialBodyFilter.SURFACE_TEMPERATURE_MIN;
                case "Surface Temperature Max": return CelestialBodyFilter.SURFACE_TEMPERATURE_MAX;
                case "Core Temperature": return CelestialBodyFilter.CORE_TEMPERATURE;
                case "Explored": return CelestialBodyFilter.EXPLORED;
                case "Radiation Level": return CelestialBodyFilter.RADIATION_LEVEL;
                case "Has Water": return CelestialBodyFilter.HAS_WATER;
                case "Surface Area": return CelestialBodyFilter.SURFACE_AREA;
                case "Mass": return CelestialBodyFilter.MASS;
                case "Gravitational Field Height": return CelestialBodyFilter.GRAVITATIONAL_FIELD_HEIGHT;
                case "Moving Speed": return CelestialBodyFilter.MOVING_SPEED;
                case "Rotation Speed": return CelestialBodyFilter.ROTATION_SPEED;
                case "Celestial Body Type": return CelestialBodyFilter.CELESTIAL_BODY_TYPE;
                case "Rotates Around Body": return CelestialBodyFilter.ROTATES_AROUND_BODY;
                default: throw new IllegalArgumentException("Unknown column name: " + columnName);
            }
        }

        private Object getValueFromPanel() {
            Component[] components = valuePanel.getComponents();
            if (components.length == 1 && components[0] instanceof JTextField) {
                return ((JTextField) components[0]).getText();
            } else if (components.length == 4 && components[1] instanceof JTextField && components[3] instanceof JTextField) {
                return new Object[]{((JTextField) components[1]).getText(), ((JTextField) components[3]).getText()};
            }
            return null;
        }
    }
}
