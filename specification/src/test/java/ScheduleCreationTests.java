
import org.concrete.Row;
import org.concrete.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleCreationTests {

    private Schedule schedule;
    private List<Row> rows;

    @BeforeEach
    public void initTestEnvironment() {
        rows = new ArrayList<>(List.of(
                new Row(new ArrayList<>(List.of("ID", "Name", "Date"))),
                new Row(new ArrayList<>(List.of("1", "Alice", "2024-08-10"))),
                new Row(new ArrayList<>(List.of("2", "Bob", "2024-08-11"))))

        );
    }

    @Test
    public void testScheduleCreationWithHeader() {
        schedule = new Schedule(true, rows);
        assertNotNull(schedule.getHeader());
        assertEquals(2, schedule.getAllRows().size());
    }

    @Test
    public void testScheduleCreationWithoutHeader() {
        schedule = new Schedule(false, rows);
        assertNull(schedule.getHeader());
        assertEquals(3, schedule.getAllRows().size());
    }
}
