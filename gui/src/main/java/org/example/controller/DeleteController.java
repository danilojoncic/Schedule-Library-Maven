package org.example.controller;

import org.concrete.Row;
import org.example.Main;
import org.example.model.Keeper;
import org.example.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteController{
    private MainFrame mainFrame;

    public DeleteController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListener();
    }

    private void attachListener(){
        mainFrame.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Keeper.getInstance().getSchedule().getAllRows().isEmpty())
                    QuickActions.deleteRowFromTable(mainFrame);
            }
        });

        mainFrame.getDeleteColumnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainFrame.getHeaderColumns().getItemCount() > 0)
                    QuickActions.deleteColumnFromTable(mainFrame);
            }
        });
    }


}
