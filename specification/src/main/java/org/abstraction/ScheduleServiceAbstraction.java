package org.abstraction;

import org.concrete.Row;
import org.concrete.Schedule;

import java.util.List;

/**
 * Interface describing the actions we can partake in with the schedule object
 */
public interface ScheduleServiceAbstraction {
    /**
     * Prints the contents of the schedule
     * @param schedule
     */
    void print(Schedule schedule);

    /**
     * Exports the schedule to the coresponding file format
     * @param schedule the shcedule that we want to export to a file format
     * @param o an object that can be a string that we use for the type of the file we want to save to
     * @return a boolean that informs us if the operation is successfull or not
     */
    boolean export(Schedule schedule,Object o);

    /**
     * Loads a schedule from the given file path
     * @param filepath path to the file that we want to load
     * @return a Schedule object with the contents of the file
     */
    Schedule load(String filepath);

    /**
     * Goes thru the schedule and returns all the rows that fullfill a cetaion parameters
     * @param schedule the schedule we want to filer thru
     * @param parameter the string
     * @return a list of rows that all contain the string parameter
     */
    List<Row> search(Schedule schedule,String parameter);
}
