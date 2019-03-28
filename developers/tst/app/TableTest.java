package app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TableTest {

    private Table table;
    private List<String> list;

    @Before
    public void setUp() {
        table = new Table("A", 10);
        list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
    }

    @Test
    public void addingReservationToTableWithCapacitySucceeds() {
        Reservation reservation = new Reservation("Owens", 3, list);
        assertTrue(table.add(reservation));
        assertEquals(1, table.reservations().size());
    }

    @Test
    public void addingReservationToTableWithoutCapacityFails() {
        Reservation reservation = new Reservation("Owens", 11, list);
        assertFalse(table.add(reservation));
    }

    @Test
    public void tablesReportRemainingCapacity() {
        assertEquals(10, table.remainingCapacity());
    }

    @Test
    public void exerciseCompareTo() {
        Table a = new Table("A", 10);
        Table b = new Table("B", 10);
        Table c = new Table("C", 5);
        Table dup = new Table("A", 5);
        assertEquals(-1, a.compareTo(b));
        assertEquals(1, a.compareTo(c));
        assertEquals(-1, b.compareTo(a));
        assertEquals(1, b.compareTo(c));
        assertEquals(-1, c.compareTo(a));
        assertEquals(-1, c.compareTo(b));
        assertEquals(0, dup.compareTo(a));
    }
}
