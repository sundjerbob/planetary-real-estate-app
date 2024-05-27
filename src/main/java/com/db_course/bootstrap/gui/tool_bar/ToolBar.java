package com.db_course.bootstrap.gui.tool_bar;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {
    public ToolBar() {
        // Creating some buttons for the toolbar
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        // Adding buttons to the toolbar
        add(button1);
        add(button2);
    }
}