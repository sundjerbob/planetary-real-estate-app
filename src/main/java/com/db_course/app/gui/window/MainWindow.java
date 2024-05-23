package com.db_course.app.gui.window;

import com.db_course.app.gui.tool_bar.ToolBar;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

    public MainWindow() {
        // Set the window title
        setTitle("Main Window");

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding toolbar
        ToolBar toolbar = new ToolBar();
        add(toolbar, java.awt.BorderLayout.PAGE_START);

        // Set the initial window size
        setSize(500, 400);

        // This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);


    }


}