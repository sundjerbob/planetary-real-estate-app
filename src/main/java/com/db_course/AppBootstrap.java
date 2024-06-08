package com.db_course;

import com.db_course.gui.MainFrame;

import javax.swing.*;

public class AppBootstrap {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });

    }
}
