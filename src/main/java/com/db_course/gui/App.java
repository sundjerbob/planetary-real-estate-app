package com.db_course.gui;

import com.db_course.gui.window.MainFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
