package com.pointaeclipseplugin.controller;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;


public class SaveAction extends Action {

    private final IWorkbenchWindow window;

    public SaveAction(String text, IWorkbenchWindow window) {
        super(text);
        this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId("saveAction");
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId("saveAction2");
    }

    public void run() {
        MessageDialog.openInformation(window.getShell(), "Open", "Rawrrrr!");
    }
}
