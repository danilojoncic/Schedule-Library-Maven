package org.example.view;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.ControllManager;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {

    private ControllManager controllManager;
    private JTextField filterTextField;
    private JButton filterButton;
    private JButton loadButton;
    private JButton exportButton;
    private JButton saveFilterButton;
    private JButton deleteColumnButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton addCalendarButton;
    private JButton createButton;
    private JTable scheduleTable;
    private JComboBox<String> headerColumns;



    public MainFrame(){
        setTitle("Client app");
        init();
        attachElements();
        attachControllers();
        this.setSize(new Dimension(800,600));
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void init(){
        addCalendarButton = new JButton("Calendar");
        saveFilterButton = new JButton("Confirm");
        deleteColumnButton = new JButton("Delete column");
        headerColumns = new JComboBox<>();
        filterTextField = new JTextField("Write anything to filter");
        filterButton = new JButton("Filter");
        loadButton = new JButton("Load");
        exportButton = new JButton("Export");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        createButton = new JButton("Create");
        scheduleTable = new JTable();
    }
    private void attachElements() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(filterTextField);
        topPanel.add(filterButton);
        topPanel.add(loadButton);
        topPanel.add(saveFilterButton);
        topPanel.add(addCalendarButton);

        JScrollPane tableScrollPane = new JScrollPane(scheduleTable);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(10, 1, 5, 5));
        rightPanel.add(createButton);
        rightPanel.add(editButton);
        rightPanel.add(deleteButton);
        rightPanel.add(exportButton);
        rightPanel.add(headerColumns);
        rightPanel.add(deleteColumnButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }


    private void attachControllers(){
        controllManager = new ControllManager(this);
    }
}
