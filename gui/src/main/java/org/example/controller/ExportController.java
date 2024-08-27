package org.example.controller;

import org.concrete.Schedule;
import org.example.model.Keeper;
import org.example.model.ScheduleTableModel;
import org.example.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExportController{

    private MainFrame mainFrame;

    public ExportController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListener();
    }

    private void attachListener(){
        mainFrame.getExportButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleTableModel stm = (ScheduleTableModel) mainFrame.getScheduleTable().getModel();
                Schedule schedule = stm.getSchedule();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save your file");
                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fullFilePath = selectedFile.getAbsolutePath();
                    System.out.println("Selected file path: " + fullFilePath);
                    Keeper.getInstance().getScheduleServiceAbstraction().export(schedule,fullFilePath);

                } else {
                    System.out.println("Save command canceled.");
                }
            }
        });

    }


}
