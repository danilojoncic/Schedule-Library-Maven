package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CalendarDialog extends JDialog {
    private JFormattedTextField startDateField;
    private JFormattedTextField endDateField;
    private JButton okButton;
    private JButton cancelButton;
    private JComboBox<String> displayOptionBox;
    private boolean isConfirmed;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CalendarDialog(JFrame parent) {
        super(parent, "Select Schedule Dates", true);
        setLayout(new BorderLayout());

        JPanel datePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        datePanel.add(new JLabel("Start Date (yyyy-MM-dd):"));

        startDateField = new JFormattedTextField(DATE_FORMAT.toFormat());
        startDateField.setValue(LocalDate.now()); // Pass LocalDate directly
        datePanel.add(startDateField);

        datePanel.add(new JLabel("End Date (yyyy-MM-dd):"));

        endDateField = new JFormattedTextField(DATE_FORMAT.toFormat());
        endDateField.setValue(LocalDate.now().plusMonths(1)); // Pass LocalDate directly
        datePanel.add(endDateField);

        add(datePanel, BorderLayout.CENTER);

        displayOptionBox = new JComboBox<>(new String[]{
                "Display Start and End Dates",
                "Display Specific Date per Row"
        });
        add(displayOptionBox, BorderLayout.NORTH);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isConfirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isConfirmed = false;
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public LocalDate getStartDate() {
        try {
            return LocalDate.parse(startDateField.getText(), DATE_FORMAT);
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDate getEndDate() {
        try {
            return LocalDate.parse(endDateField.getText(), DATE_FORMAT);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean shouldDisplayStartAndEndDates() {
        return displayOptionBox.getSelectedIndex() == 0;
    }
}
