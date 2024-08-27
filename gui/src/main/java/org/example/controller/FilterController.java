package org.example.controller;

import org.concrete.Row;
import org.concrete.Schedule;
import org.example.model.Keeper;
import org.example.model.ScheduleTableModel;
import org.example.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FilterController{
    private MainFrame mainFrame;

    public FilterController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListner();
    }

    private void attachListner(){
        mainFrame.getFilterButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                QuickActions.filterAndMakeTemporaryScheduleTableModel(mainFrame);
            }
        });
    }


}
