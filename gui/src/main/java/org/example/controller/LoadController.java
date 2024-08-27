package org.example.controller;

import org.example.model.Keeper;
import org.example.model.ScheduleTableModel;
import org.example.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadController{
    private MainFrame mainFrame;

    public LoadController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListner();
    }

    private void attachListner(){
        mainFrame.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select a file that contains a schedule");

                int userSelection = fileChooser.showOpenDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToOpen = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + fileToOpen.getAbsolutePath());
                    Keeper.getInstance().setSchedule(
                            Keeper.getInstance().getScheduleServiceAbstraction().load(fileToOpen.getAbsolutePath()));
                }
                ScheduleTableModel stm = new ScheduleTableModel(Keeper.getInstance().getSchedule());
                mainFrame.getScheduleTable().setModel(stm);
                QuickActions.resetComboBox(mainFrame);
            }
        });
    }

}
