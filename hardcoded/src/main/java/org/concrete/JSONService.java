package org.concrete;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abstraction.IO;

import java.io.File;
import java.io.IOException;

/**
 * This class deals with json file IO
 */
public class JSONService implements IO {
    private String input;
    private String output;

    public JSONService() {
    }

    public JSONService(String input, String output) {
        this.input = input;
        this.output = output;
    }

    /**
     * Reads the file from the filepath, and creates a new Schedule object
     * @param filepath the path to the file we want to read
     * @return an Object that should be cast into a Schedule
     */
    @Override
    public Object read(String filepath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Schedule schedule = objectMapper.readValue(new File(filepath), Schedule.class);
            schedule.initUnifiedMap();
            return schedule;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Creates and writes to a json file tje contents of the Schedule
     * @param object and Object that should be cast into a Schedule
     */
    @Override
    public void write(Object object) {
        if (object instanceof Schedule) {
            Schedule schedule = (Schedule) object;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(output), schedule);
                System.out.println("JSON written to " + output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Object provided is not of type Schedule");
        }

    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
