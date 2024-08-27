import org.concrete.Row;
import org.concrete.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeaderOperationsTests {
    private Schedule schedule;
    private List<Row> rows = new ArrayList<>();

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
    public void testCreateHeaderSuccess() {
        List<String> headerContent = Arrays.asList("ID", "Name", "Date");
        assertTrue(schedule.createHeader(headerContent));
    }

    @Test
    public void testCreateHeaderFailure() {
        List<String> headerContent = Arrays.asList("ID", "Name");
        assertFalse(schedule.createHeader(headerContent)); // Mismatched size
    }

    @Test
    public void testAddHeaderColumnSuccess() {
        assertTrue(schedule.addHeaderColumn("Location"));
        assertEquals(4, schedule.getHeader().getContents().size());
    }

    @Test
    public void testRemoveHeaderColumnSuccess() {
        assertTrue(schedule.removeHeaderColumn(1));
        assertEquals(2, schedule.getHeader().getContents().size());
    }

    @Test
    public void testRemoveHeaderColumnFailure() {
        assertFalse(schedule.removeHeaderColumn(-1)); // Invalid index
    }
}
