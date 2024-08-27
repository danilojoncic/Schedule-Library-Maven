import static org.junit.jupiter.api.Assertions.*;
import org.concrete.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVServiceTest {

    private static final String CSV_FILE = "src/test/resources/test.csv";
    private static final String OUTPUT_CSV = "src/test/resources/output.csv";


    @Test
    void testRead() throws IOException {
        CSVService csvService = new CSVService();
        Schedule schedule = (Schedule) csvService.read(CSV_FILE);

        assertNotNull(schedule);
        assertTrue(schedule.isContainsHeader());
        assertEquals("header1", schedule.getHeader().getContents().get(0));
    }


    @Test
    void testWrite() throws IOException {
        Row header = new Row(List.of("header1", "header2"));
        Row row1 = new Row(List.of("field1", "field2"));
        Row row2 = new Row(List.of("abc", "def"));
        List<Row> rows = List.of(header, row1, row2);
        Schedule schedule = new Schedule(true, new ArrayList<>(rows));

        CSVService csvService = new CSVService(OUTPUT_CSV, null);
        csvService.write(schedule);

        try (var reader = new BufferedReader(new FileReader(OUTPUT_CSV))) {
            String firstLine = reader.readLine();
            assertEquals("\"header1\",\"header2\"", firstLine);
        }
    }
    @AfterAll
    static void cleanUp(){
        new File(OUTPUT_CSV).delete();
    }
}
