package com.db_course.gui.filter_panels;

import com.db_course.be.filter.defs.FilterOperation;
import com.db_course.be.filter.entity_filters.impl.CelestialTypeFilter;
import com.db_course.be.service.CelestialTypeService;
import com.db_course.gui.tables.model.CelestialTypeTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CelestialTypeFilterPanel extends JPanel {
    private final CelestialTypeTableModel tableModel;
    private final FilterContainer filterContainer;
    private final JButton addFilterButton;
    private final JButton applyFilterButton;

    public CelestialTypeFilterPanel(CelestialTypeTableModel tableModel) {
        this.tableModel = tableModel;
        setLayout(new BorderLayout());

        filterContainer = new FilterContainer();

        JScrollPane scrollPane = new JScrollPane(filterContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addFilterButton = new JButton("Add Filter");
        applyFilterButton = new JButton("Apply Filters");

        addFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterContainer.addNewFilter();
            }
        });

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

    private void applyFilters() {
        CelestialTypeFilter filter = new CelestialTypeFilter();
        for (FilterComponent component : filterContainer.getFilterComponents()) {
            component.applyFilter(filter);
        }
        tableModel.clear();
        CelestialTypeService.getInstance().processFilteredCelestialTypes(tableModel::addCelestialType, filter);
    }

    private class FilterContainer extends JPanel {
        private final List<FilterComponent> filterComponents = new ArrayList<>();
        private int yOffset = 0;

        public FilterContainer() {
            setLayout(null);
        }

        public void addNewFilter() {
            FilterComponent filterComponent = new FilterComponent(this);
            filterComponent.setBounds(10, yOffset, 760, 50);
            filterComponents.add(filterComponent);
            add(filterComponent);
            yOffset += 60;
            revalidate();
            repaint();
        }

        public List<FilterComponent> getFilterComponents() {
            return new ArrayList<>(filterComponents);
        }

        public void removeFilterComponent(FilterComponent filterComponent) {
            filterComponents.remove(filterComponent);
            remove(filterComponent);
            revalidate();
            repaint();
            yOffset -= 60;
            for (int i = 0; i < filterComponents.size(); i++) {
                filterComponents.get(i).setBounds(10, i * 60, 760, 50);
            }
        }
    }

    private class FilterComponent extends JPanel {
        private final JComboBox<String> columnComboBox;
        private final JComboBox<FilterOperation> operationComboBox;
        private final JPanel valuePanel;
        private final JButton removeButton;

        private final FilterContainer parentContainer;

        public FilterComponent(FilterContainer parentContainer) {
            this.parentContainer = parentContainer;
            setLayout(null);
            setBorder(BorderFactory.createLineBorder(Color.BLACK));

            columnComboBox = new JComboBox<>(new String[]{
                    "ID", "Name", "Description"
            });
            columnComboBox.setBounds(10, 10, 120, 30);

            operationComboBox = new JComboBox<>(FilterOperation.values());
            operationComboBox.setBounds(140, 10, 120, 30);

            valuePanel = new JPanel();
            valuePanel.setBounds(270, 10, 200, 30);
            valuePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            updateValuePanel();

            removeButton = new JButton("Remove");
            removeButton.setBounds(480, 10, 80, 30);
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parentContainer.removeFilterComponent(FilterComponent.this);
                }
            });

            columnComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateValuePanel();
                }
            });

            operationComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateValuePanel();
                }
            });

            add(columnComboBox);
            add(operationComboBox);
            add(valuePanel);
            add(removeButton);
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

        public void applyFilter(CelestialTypeFilter filter) {
            String selectedColumn = (String) columnComboBox.getSelectedItem();
            FilterOperation selectedOperation = (FilterOperation) operationComboBox.getSelectedItem();
            if (selectedColumn != null && selectedOperation != null) {
                int columnIndex = getColumnIndex(selectedColumn);
                Object value = getValueFromPanel();
                filter.addFilter(columnIndex, selectedOperation, value);
            }
        }

        private int getColumnIndex(String columnName) {
            return switch (columnName) {
                case "ID" -> CelestialTypeFilter.ID;
                case "Name" -> CelestialTypeFilter.NAME;
                case "Description" -> CelestialTypeFilter.DESCRIPTION;
                default -> throw new IllegalArgumentException("Unknown column name: " + columnName);
            };
        }

        private Object getValueFromPanel() {
            // For simplicity, assuming single text field for all types
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
