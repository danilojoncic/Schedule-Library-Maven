package org.concrete;

import org.abstraction.IO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class deals with csv file IO
 */
public class CSVService implements IO {

    private String output;
    private String input;

    public CSVService() {
    }

    public CSVService(String output, String input) {
        this.output = output;
        this.input = input;
    }

    /**
     * Reads the file from the filepath, and creates a new Schedule object
     * @param filepath the path to the file we want to read
     * @return an Object that should be cast into a Schedule
     */
    @Override
    public Object read(String filepath) {
        try (FileReader reader = new FileReader(filepath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withQuoteMode(org.apache.commons.csv.QuoteMode.ALL))) {

            List<String> headers = new ArrayList<>(csvParser.getHeaderNames());
            Row header = new Row(headers);

            List<Row> rows = new ArrayList<>();
            if(!headers.isEmpty()){
                rows.add(header);
            }
            for (CSVRecord record : csvParser) {
                List<String> contents = new ArrayList<>();
                for (String value : record) {
                    contents.add(value);
                }
                rows.add(new Row(contents));
            }
            return new Schedule(!headers.isEmpty(), rows);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates and writes to a csv file tje contents of the Schedule
     * @param object and Object that should be cast into a Schedule
     */
    @Override
    public void write(Object object) {
        String csvFile = output;

        if (object instanceof Schedule) {
            Schedule schedule = (Schedule) object;

            try (FileWriter fw = new FileWriter(csvFile);
                 CSVPrinter csvPrinter = schedule.isContainsHeader()
                         ? new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader(schedule.getHeader().getContents().toArray(new String[0])).withQuoteMode(org.apache.commons.csv.QuoteMode.ALL))
                         : new CSVPrinter(fw, CSVFormat.DEFAULT.withQuoteMode(org.apache.commons.csv.QuoteMode.ALL))) {

                for (Row row : schedule.getAllRows()) {
                    csvPrinter.printRecord(row.getContents());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Object provided is not of type Schedule");
        }
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
