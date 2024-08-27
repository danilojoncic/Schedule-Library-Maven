package org.example.controller;

import org.example.view.AddDialog;
import org.example.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddController{
    private MainFrame mainFrame;

    public AddController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        attachListner();
    }


    private void attachListner(){
        mainFrame.getCreateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog addDialog = new AddDialog(mainFrame);
                addDialog.setVisible(true);
            }
        });
    }


}
