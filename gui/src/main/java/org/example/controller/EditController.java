package org.example.controller;

import org.concrete.Row;
import org.example.model.Keeper;
import org.example.view.EditDialog;
import org.example.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController{
    private MainFrame mainFrame;

    public EditController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListener();
    }


    private void attachListener(){
        mainFrame.getEditButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int editIndex = mainFrame.getScheduleTable().getSelectedRow();
                Row row = Keeper.getInstance().getSchedule().getAllRows().get(editIndex);
                EditDialog ed = new EditDialog(mainFrame,row);
                ed.setVisible(true);
            }
        });

    }


}
