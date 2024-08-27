
import org.concrete.Row;
import org.concrete.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowAdditionsTests {

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
    public void testAddRowSuccess() {
        Row newRow = new Row(new ArrayList<>(List.of("3", "Charlie", "2024-08-12")));
        assertTrue(schedule.addRow(newRow));
        assertEquals(3, schedule.getAllRows().size());
    }

    @Test
    public void testAddRowFailureDueToNull() {
        assertFalse(schedule.addRow(null));
    }

    @Test
    public void testAddRowFailureDueToMismatchedSize() {
        Row newRow = new Row(new ArrayList<>(List.of("4", "David")));
        assertFalse(schedule.addRow(newRow)); // Mismatched size
    }
}
