package com.db_course.app;

import com.db_course.app.gui.window.MainWindow;

public class AppCore {

    public static void main(String[] args) {
        initApp();
    }

    private static void initApp() {
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}
