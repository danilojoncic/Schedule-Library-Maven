package org.example.controller;

import org.concrete.Row;
import org.concrete.Schedule;
import org.example.model.Keeper;
import org.example.model.ScheduleTableModel;
import org.example.view.MainFrame;

import java.util.List;

public class QuickActions {

    public static void resetComboBox(MainFrame mainFrame){
        mainFrame.getHeaderColumns().removeAllItems();
        Keeper.getInstance().getSchedule().getHeader().getContents().forEach(header->mainFrame.getHeaderColumns().addItem(header));
    }

    public static void deleteRowFromTable(MainFrame mainFrame){
        int rowIndex = mainFrame.getScheduleTable().getSelectedRow();
        Keeper.getInstance().getSchedule().getAllRows().remove(rowIndex);
        Keeper.getInstance().getSchedule().reformUnifiedMap();
        //really slow
        mainFrame.getScheduleTable().setModel(new ScheduleTableModel(Keeper.getInstance().getSchedule()));
    }

    public static void deleteColumnFromTable(MainFrame mainFrame){
        int indexFromComboBox = mainFrame.getHeaderColumns().getSelectedIndex();
        Keeper.getInstance().getSchedule().removeHeaderColumn(indexFromComboBox);
        mainFrame.getScheduleTable().setModel(new ScheduleTableModel(Keeper.getInstance().getSchedule()));
        mainFrame.getHeaderColumns().removeAllItems();
        Keeper.getInstance().getSchedule().getHeader().getContents().forEach(header->mainFrame.getHeaderColumns().addItem(header));
    }

    public static void filterAndMakeTemporaryScheduleTableModel(MainFrame mainFrame){
        List<Row> filteredRows = Keeper.getInstance().getScheduleServiceAbstraction().search(
                Keeper.getInstance().getSchedule(), mainFrame.getFilterTextField().getText()
        );
        if(filteredRows == null || filteredRows.isEmpty())return;
        filteredRows.add(0,Keeper.getInstance().getSchedule().getHeader());
        Schedule scheduleFiltered = new Schedule(true,filteredRows);
        ScheduleTableModel newStm = new ScheduleTableModel(scheduleFiltered);
        mainFrame.getScheduleTable().setModel(newStm);
    }
    public static void confirmFiltrationAndMakeTempTheMainSchedule(MainFrame mainFrame){
        ScheduleTableModel stm = (ScheduleTableModel) mainFrame.getScheduleTable().getModel();
        Schedule schedule = stm.getSchedule();
        Keeper.getInstance().setSchedule(schedule);
    }
    public static void refreshScheduleTableFromKeeper(MainFrame mainFrame){
        mainFrame.getScheduleTable().setModel(new ScheduleTableModel(Keeper.getInstance().getSchedule()));
    }
}
