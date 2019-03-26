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
    public void createsTable() {
        assertTrue(table instanceof Table);
    }

    @Test
    public void adding_reservation_to_table_with_capacity_succeeds() {
        Reservation reservation = new Reservation("Owens", 3, list);
        assertTrue(table.add(reservation));
    }

    @Test
    public void adding_reservation_to_table_without_capacity_fails() {
        Reservation reservation = new Reservation("Owens", 11, list);
        assertFalse(table.add(reservation));
    }

    @Test
    public void tablesReportCapacity() {
        assertEquals(10, table.getCapacity());
    }
}
