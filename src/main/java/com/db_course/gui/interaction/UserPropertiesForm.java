package com.db_course.gui.interaction;

import com.db_course.be.service.PropertyService;
import com.db_course.dto.PropertyDto;
import com.db_course.dto.UserDto;

import javax.swing.*;
import java.awt.*;

public class UserPropertiesForm extends JDialog {

    private UserDto user;
    private JList<PropertyDto> propertiesList;
    private DefaultListModel<PropertyDto> propertiesListModel;

    public UserPropertiesForm(Frame owner, UserDto user) {
        super(owner, "My Properties", true);
        this.user = user;
        initializeComponents();
        layoutComponents();
        fetchUserProperties();
    }

    private void initializeComponents() {
        propertiesListModel = new DefaultListModel<>();
        propertiesList = new JList<>(propertiesListModel);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        add(new JScrollPane(propertiesList), BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(getOwner());
    }

    private void fetchUserProperties() {
        PropertyService.getInstance().processPropertiesOwnedByUser(user.getId(), propertiesListModel::addElement);
    }
}
