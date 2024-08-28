package org.usable;
import org.abstraction.ScheduleServiceAbstraction;
import org.concrete.*;
import org.hardcode.RAFService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * This class is tasked with implementing the service actions based on the specification
 */
public class Implementation implements ScheduleServiceAbstraction {

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean simpleImplementation;

    static {
        Manager.setScheduleService(new Implementation());
    }

    @Override
    public void print(Schedule schedule) {
        if(schedule.isContainsHeader()){
            System.out.println(schedule.getHeader());
        }
        schedule.getAllRows().forEach(row-> System.out.println(row));
    }

    @Override
    public boolean export(Schedule schedule, Object o) {
        String output = (String) o;
        RAFService.implementDesiredFormat(schedule,startDate,endDate,simpleImplementation);
        if(output.endsWith(".json")){
            JSONService jsonService = new JSONService();
            jsonService.setOutput(output);
            jsonService.write(schedule);
            return true;
        }else if(output.endsWith(".csv")){
            CSVService csvService = new CSVService();
            csvService.setOutput(output);
            csvService.write(schedule);
            return true;
        }else{
            //wrong file extension
            return false;
        }
    }

    @Override
    public Schedule load(String filepath) {
        if(filepath.endsWith(".json")){
            JSONService jsonService = new JSONService();
            jsonService.setInput(filepath);
            return (Schedule) jsonService.read(filepath);
        }else if(filepath.endsWith(".csv")){
            CSVService csvService = new CSVService();
            csvService.setInput(filepath);
            return (Schedule) csvService.read(filepath);
        }else{
            //wrong file extension
            return null;
        }
    }

    @Override
    public List<Row> search(Schedule schedule,String parameter) {
        if(parameter == null || parameter.isEmpty() || parameter.isBlank()){
            return schedule.getAllRows();
        }else{
            return schedule.filter(parameter);
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isSimpleImplementation() {
        return simpleImplementation;
    }

    public void setSimpleImplementation(boolean simpleImplementation) {
        this.simpleImplementation = simpleImplementation;
    }
}
