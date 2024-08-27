package org.example.controller;

import org.example.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmController {
    private MainFrame mainFrame;

    public ConfirmController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListner();
    }


    private void attachListner(){
        mainFrame.getSaveFilterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuickActions.confirmFiltrationAndMakeTempTheMainSchedule(mainFrame);
            }
        });
    }
}
