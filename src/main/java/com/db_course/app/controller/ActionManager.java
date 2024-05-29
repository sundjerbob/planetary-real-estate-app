package com.db_course.app.controller;

import com.db_course.app.controller.actions.DeleteAction;
import com.db_course.app.controller.actions.ReloadDataAction;
import com.db_course.app.controller.actions.SaveAction;
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
