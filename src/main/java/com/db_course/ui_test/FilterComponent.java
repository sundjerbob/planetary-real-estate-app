package com.db_course.ui_test;
import com.db_course.dto.UserDto;

import javax.swing.*;

public interface FilterComponent {
    JComponent getComponent();
    boolean applyFilter(UserDto user);
}
