package org.example.controller;

import org.example.view.MainFrame;

import java.util.logging.Filter;

public class ControllManager {
    private MainFrame mainFrame;
    private AddController addController;
    private DeleteController deleteController;
    private EditController editController;
    private FilterController filterController;
    private LoadController loadController;
    private ExportController exportController;
    private ConfirmController confirmController;
    private CalendarController calendarController;

    public ControllManager(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachControllers(mainFrame);
    }

    private void attachControllers(MainFrame mainFrame){
        calendarController = new CalendarController(mainFrame);
        confirmController = new ConfirmController(mainFrame);
        addController = new AddController(mainFrame);
        deleteController = new DeleteController(mainFrame);
        filterController = new FilterController(mainFrame);
        loadController = new LoadController(mainFrame);
        exportController = new ExportController(mainFrame);
        editController = new EditController(mainFrame);
    }
}
