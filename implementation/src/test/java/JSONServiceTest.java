import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.concrete.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONServiceTest {

    static Schedule schedule;
    private static final String JSON_FILE = "src/test/resources/test.json";
    private static final String OUTPUT_JSON = "src/test/resources/output.json";


    @BeforeAll
    static void initTestEnvironment(){
        Row header = new Row(List.of("header1", "header2"));
        Row row1 = new Row(List.of("field1", "field2"));
        Row row2 = new Row(List.of("abc", "def"));
        List<Row> rows = List.of(header, row1, row2);
        schedule = new Schedule(true, new ArrayList<>(rows));
    }

    @Test
    void testRead() throws IOException {
        JSONService jsonService = new JSONService();
        Schedule scheduleRead = (Schedule) jsonService.read(JSON_FILE);

        Assertions.assertAll(
                ()->assertNotNull(scheduleRead),
                ()->assertTrue(scheduleRead.isContainsHeader()),
                ()->assertEquals("header1", scheduleRead.getHeader().getContents().get(0))
        );
    }


    @Test
    void testWrite() throws IOException {
        JSONService jsonService = new JSONService();
        jsonService.setOutput(OUTPUT_JSON);
        jsonService.write(schedule);

        ObjectMapper objectMapper = new ObjectMapper();
        Schedule writtenSchedule = objectMapper.readValue(new File(OUTPUT_JSON), Schedule.class);

        Assertions.assertAll(
                ()->assertNotNull(writtenSchedule),
                ()->assertTrue(writtenSchedule.isContainsHeader()),
                ()->assertEquals("header1", writtenSchedule.getHeader().getContents().get(0))
        );

    }

    @AfterAll
    static void cleanUp(){
        new File(OUTPUT_JSON).delete();
    }
}
