package app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlannerTest {

    private Planner planner;
    private List<String> list;

    @Before
    public void setUp() {
        planner = new Planner();
        list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
    }

    @Test
    public void createPlanner() {
        assertTrue(planner instanceof Planner);
    }

    @Test
    public void plannersAcceptTables() {
        Table table = new Table("A", 10);
        planner.add(table);
        assertEquals(1, planner.tableCount());
    }

    @Test
    public void plannersReportTotalCapacity() {
        Table table = new Table("A", 10);
        Table table1 = new Table("B", 10);
        planner.add(table);
        planner.add(table1);
        assertEquals(2, planner.tableCount());
        assertEquals(20, planner.getTotalCapacity());
    }

    @Test
    public void plannersDoNotAcceptDuplicateTableIds() {
        Table table = new Table("A", 10);
        Table table1 = new Table("A", 10);
        planner.add(table);
        planner.add(table1);
        assertEquals(1, planner.tableCount());
        assertEquals(10, planner.getTotalCapacity());
    }

    @Test
    public void plannersAcceptReservations() {
        Reservation reservation = new Reservation("Owens", 3, list);
        planner.add(reservation);
        assertEquals(1, planner.reservationCount());
    }

    @Test
    public void plannersDoNotAcceptDuplicateReservationIds() {
        Reservation reservation = new Reservation("Owens", 3, list);
        Reservation reservation1 = new Reservation("Owens", 3, list);
        planner.add(reservation);
        planner.add(reservation1);
        assertEquals(1, planner.reservationCount());
    }

    @Test
    public void plannersReportsTotalNumberOfReservations() {
        Reservation reservation = new Reservation("Owens", 3, list);
        Reservation reservation1 = new Reservation("Smith", 3, list);
        planner.add(reservation);
        planner.add(reservation1);
        assertEquals(2, planner.reservationCount());
        assertEquals(6, planner.totalReservations());
    }
}
