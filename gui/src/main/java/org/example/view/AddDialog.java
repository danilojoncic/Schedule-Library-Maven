package org.example.view;

import org.concrete.Row;
import org.example.controller.QuickActions;
import org.example.model.Keeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddDialog extends JDialog {
    private JTextField[] textFields;
    private JButton addButton;
    private JButton cancelButton;

    public AddDialog(JFrame parent) {
        super(parent, "Add New Row", true);
        setLayout(new BorderLayout());

        int fieldCount = Keeper.getInstance().getSchedule().getHeader().getContents().size();
        textFields = new JTextField[fieldCount];

        JPanel panel = new JPanel(new GridLayout(fieldCount, 2, 10, 10));

        for (int i = 0; i < fieldCount; i++) {
            panel.add(new JLabel(Keeper.getInstance().getSchedule().getHeader().getContents().get(i)));
            textFields[i] = new JTextField();
            panel.add(textFields[i]);
        }

        add(panel, BorderLayout.CENTER);

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] newRowData = new String[fieldCount];
                for (int i = 0; i < textFields.length; i++) {
                    newRowData[i] = textFields[i].getText();
                }
                Row newRow = new Row(List.of(newRowData));
                Keeper.getInstance().getSchedule().addRow(newRow);
                Keeper.getInstance().getSchedule().reformUnifiedMap();
                QuickActions.refreshScheduleTableFromKeeper((MainFrame) parent);
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
