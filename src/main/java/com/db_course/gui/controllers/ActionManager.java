package com.db_course.gui.controllers;

import com.db_course.gui.controllers.toolbar_actions.DeleteAction;
import com.db_course.gui.controllers.toolbar_actions.ReloadDataAction;
import com.db_course.gui.controllers.toolbar_actions.SaveAction;
import lombok.Getter;

@Getter
public class ActionManager {


    private static volatile ActionManager instance = new ActionManager();
    private static final Object mutex = new Object();
    private final SaveAction saveAction;
    private final ReloadDataAction reloadDataAction;
    private final DeleteAction deleteAction;


    private ActionManager() {
        saveAction = new SaveAction();
        reloadDataAction = new ReloadDataAction();
        deleteAction = new DeleteAction();
    }


    public static ActionManager getInstance() {

        if (instance == null) {
            synchronized (mutex) {
                if (instance == null)
                    instance = new ActionManager();
            }
        }

        return instance;
    }

}
