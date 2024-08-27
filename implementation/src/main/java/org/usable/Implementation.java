package org.usable;
import org.abstraction.ScheduleServiceAbstraction;
import org.concrete.*;

import java.util.List;

/**
 * This class is tasked with implementing the service actions based on the specification
 */
public class Implementation implements ScheduleServiceAbstraction {

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
}
