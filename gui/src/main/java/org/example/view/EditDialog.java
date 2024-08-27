package org.example.view;

import org.concrete.Row;
import org.example.controller.QuickActions;
import org.example.model.Keeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDialog extends JDialog {
    private JTextField[] textFields;
    private JButton saveButton;
    private JButton cancelButton;

    public EditDialog(JFrame parent, Row row) {
        super(parent, "Edit Row", true);
        setLayout(new BorderLayout());

        String[] rowData = row.getContents().toArray(new String[0]);
        textFields = new JTextField[rowData.length];

        JPanel panel = new JPanel(new GridLayout(rowData.length, 2, 10, 10));

        for (int i = 0; i < rowData.length; i++) {
            panel.add(new JLabel(Keeper.getInstance().getSchedule().getHeader().getContents().get(i)));
            textFields[i] = new JTextField(rowData[i]);
            panel.add(textFields[i]);
        }

        add(panel, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < textFields.length;i++){
                    Keeper.getInstance().getSchedule().modifyRowField(row,i,textFields[i].getText());
                    QuickActions.refreshScheduleTableFromKeeper((MainFrame) parent);
                }
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(parent);
    }
}

