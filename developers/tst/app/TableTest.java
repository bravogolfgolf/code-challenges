package app;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TableTest {

    @Test
    public void createsTable() {
        Table table = new Table("A", 10);
        assertTrue(table instanceof Table);
    }

    @Test
    public void adding_reservation_to_table_with_capacity_succeeds() {
        Table table = new Table("A", 10);
        List<String> list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
        Reservation reservation = new Reservation("Owens", 3, list);

        assertTrue(table.add(reservation));
    }

    @Test
    public void adding_reservation_to_table_without_capacity_fails() {
        Table table = new Table("A", 10);
        List<String> list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
        Reservation reservation = new Reservation("Owens", 11, list);

        assertFalse(table.add(reservation));
    }
}
