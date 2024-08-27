import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.usable.Implementation;
import org.concrete.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    private Implementation impl;
    private static final String CSV_FILE = "src/test/resources/test.csv";
    private static final String JSON_FILE = "src/test/resources/test.json";

    @BeforeEach
    void initTestEnvironment() {
        impl = new Implementation();
        new File(CSV_FILE).delete();
        new File(JSON_FILE).delete();
    }

    @Test
    void testCsvToJson() {
        Row header = new Row(List.of("header1", "header2"));
        Row row1 = new Row(List.of("field1", "field2"));
        Row row2 = new Row(List.of("abc", "def"));
        List<Row> rows = new ArrayList<>(List.of(header, row1, row2));
        Schedule schedule = new Schedule(true, rows);

        impl.export(schedule, CSV_FILE);

        Schedule loadedSchedule = impl.load(CSV_FILE);
        impl.export(loadedSchedule, JSON_FILE);

        Schedule jsonSchedule = impl.load(JSON_FILE);

        Assertions.assertAll(
                ()->assertNotNull(jsonSchedule),
                ()->assertEquals(schedule.getAllRows().get(0).toString(), jsonSchedule.getAllRows().get(0).toString()),
                ()->assertEquals(schedule.getHeader().getContents().toString(), jsonSchedule.getHeader().getContents().toString())
        );
    }

    @Test
    void testJsonToCsv() {
        Row header = new Row(List.of("header1", "header2"));
        Row row1 = new Row(List.of("field1", "field2"));
        Row row2 = new Row(List.of("abc", "def"));
        List<Row> rows = new ArrayList<>(List.of(header, row1, row2));
        Schedule schedule = new Schedule(true,rows);

        impl.export(schedule, JSON_FILE);

        Schedule loadedSchedule = impl.load(JSON_FILE);
        impl.export(loadedSchedule, CSV_FILE);

        Schedule csvSchedule = impl.load(CSV_FILE);

        Assertions.assertAll(
                ()->assertNotNull(csvSchedule),
                ()->assertEquals(schedule.getAllRows().get(1).getContents().toString(), csvSchedule.getAllRows().get(1).getContents().toString()),
                ()->assertEquals(schedule.getHeader().getContents().toString(), csvSchedule.getHeader().getContents().toString())

        );
    }

    @Test
    void testAddNewColumnToHeaderAndExport() {
        Row header = new Row(new ArrayList<>(List.of("header1", "header2")));
        Row row1 = new Row(new ArrayList<>(List.of("field1", "field2")));
        Row row2 = new Row(new ArrayList<>(List.of("abc", "def")));
        List<Row> rows = new ArrayList<>(List.of(header, row1, row2));
        Schedule schedule = new Schedule(true,rows);

        schedule.addHeaderColumn("newColumn");

        impl.export(schedule, CSV_FILE);
        impl.export(schedule, JSON_FILE);

        Schedule csvSchedule = impl.load(CSV_FILE);
        Schedule jsonSchedule = impl.load(JSON_FILE);
        Assertions.assertAll(
                ()->assertNotNull(csvSchedule),
                ()->assertNotNull(jsonSchedule),
                ()->assertEquals(schedule.getAllRows().get(1).getContents().toString(), csvSchedule.getAllRows().get(1).getContents().toString()),
                ()->assertEquals(schedule.getAllRows().get(1).getContents().toString(), jsonSchedule.getAllRows().get(1).getContents().toString()),
                ()->assertEquals(schedule.getHeader().getContents().toString(), csvSchedule.getHeader().getContents().toString()),
                ()->assertEquals(schedule.getHeader().getContents().toString(), jsonSchedule.getHeader().getContents().toString())
        );
  }

    @Test
    void testFilterReformSchedule() {
        Row header = new Row(List.of("header1", "header2"));
        Row row1 = new Row(List.of("field1", "field2"));
        Row row2 = new Row(List.of("abc", "def"));
        List<Row> rows = new ArrayList<>(List.of(header, row1, row2));
        Schedule schedule = new Schedule(true,  rows);

        impl.export(schedule, JSON_FILE);

        Schedule loadedSchedule = impl.load(JSON_FILE);

        List<Row> filteredRows = impl.search(loadedSchedule, "field1");
        filteredRows.add(schedule.getHeader());
        Schedule scheduleNew = new Schedule(true,filteredRows);
        assertNotNull(scheduleNew);
    }
}
