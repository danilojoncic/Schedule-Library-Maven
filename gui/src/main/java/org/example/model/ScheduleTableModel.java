package org.example.model;

import org.concrete.Schedule;

import javax.swing.table.AbstractTableModel;
import java.util.AbstractList;

public class ScheduleTableModel extends AbstractTableModel {

    private Schedule schedule;

    public ScheduleTableModel(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public int getRowCount() {
        return schedule.getAllRows().size();
    }

    @Override
    public int getColumnCount() {
        return schedule.getHeader().getContents().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return  schedule.getAllRows().get(rowIndex).getContents().get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //editing will be done using the built in methods
        return false;
    }

    @Override
    public String getColumnName(int column) {
        if(schedule.isContainsHeader()){
            return schedule.getHeader().getContents().get(column);
        }else{
            return "HEADER";
        }
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
