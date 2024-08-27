
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.concrete.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.usable.Implementation;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ImplementationTests {

    private Schedule schedule;
    private Implementation impl;

    @BeforeEach
    void initTestEnvironment() {
        impl = new Implementation();
        Row header = new Row(List.of("header1", "header2"));
        Row row1 = new Row(List.of("field1", "field2"));
        Row row2 = new Row(List.of("abc", "def"));
        List<Row> rows = List.of(header, row1, row2);
        schedule = new Schedule(true, new ArrayList<>(rows));
    }

    @Test
    void testPrintWithHeader() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        impl.print(schedule);
        String output = outputStream.toString();

        Row header = schedule.getHeader();
        Row row1 = schedule.getAllRows().get(0);
        Row row2 = schedule.getAllRows().get(1);

        assertTrue(output.contains(header.toString()));
        assertTrue(output.contains(row1.toString()));
        assertTrue(output.contains(row2.toString()));
    }

    @Test
    void testSearch() {
        List<Row> result = impl.search(schedule, "field1");
        Row row1 = schedule.getAllRows().get(0);
        assertTrue(result.contains(row1));
    }

    @Test
    void searchWithEmptyParameter(){
        List<Row> result = impl.search(schedule,"");
        assertEquals(schedule.getAllRows(),result);
    }
}
