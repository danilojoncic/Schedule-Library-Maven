
import org.concrete.Row;
import org.concrete.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleEditTests {

    private Schedule schedule;
    private List<Row> rows;

    @BeforeEach
    public void initTestEnvironment() {
        rows = new ArrayList<>(List.of(
                new Row(new ArrayList<>(List.of("ID", "Name", "Date"))),
                new Row(new ArrayList<>(List.of("1", "Alice", "2024-08-10"))),
                new Row(new ArrayList<>(List.of("2", "Bob", "2024-08-11"))))

        );
        schedule = new Schedule(true, rows);
    }

    @Test
    public void testModifyRowFieldSuccess() {
        Row row = schedule.getAllRows().get(0);
        assertTrue(schedule.modifyRowField(row, 1, "Alice Updated"));
        assertEquals("Alice Updated", row.getContents().get(1));
    }

    @Test
    public void testModifyRowFieldFailure() {
        Row row = schedule.getAllRows().get(0);
        assertFalse(schedule.modifyRowField(row, 3, "Invalid")); // Invalid index
    }

    @Test
    public void testDeleteRowSuccess() {
        Row row = schedule.getAllRows().get(0);
        assertTrue(schedule.deleteRow(row));
        assertEquals(1, schedule.getAllRows().size());
    }

    @Test
    public void testDeleteRowFailure() {
        Row row = new Row(new ArrayList<>(List.of("Invalid", "Row", "Data")));
        assertFalse(schedule.deleteRow(row)); // Row doesn't exist
    }
}
