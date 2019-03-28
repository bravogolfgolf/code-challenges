package app;

import app.Planner.InsufficientSeatingCapacity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlannerTest {

    private Planner planner;
    private List<String> emptyList = new ArrayList<>();

    @Before
    public void setUp() {
        planner = new Planner();
    }

    @Test
    public void createPlanner() {
        assertTrue(planner instanceof Planner);
    }

    @Test
    public void plannerAcceptTables() {
        Table table = new Table("A", 10);
        planner.add(table);
        assertEquals(1, planner.tableCount());
    }

    @Test
    public void plannerDoesNotAcceptDuplicateTableIds() {
        Table table = new Table("A", 10);
        Table table1 = new Table("A", 10);
        planner.add(table);
        planner.add(table1);
        assertEquals(1, planner.tableCount());
        assertEquals(10, planner.tableRemainingCapacity());
    }

    @Test
    public void plannerReportsRemainingTableCapacity() {
        Table table = new Table("A", 10);
        Table table1 = new Table("B", 10);
        planner.add(table);
        planner.add(table1);
        assertEquals(2, planner.tableCount());
        assertEquals(20, planner.tableRemainingCapacity());
    }

    @Test
    public void plannerAcceptsReservations() {
        Reservation reservation = new Reservation("Owens", 3, emptyList);
        planner.add(reservation);
        assertEquals(1, planner.reservationCount());
    }

    @Test
    public void plannerDoesNotAcceptDuplicateReservationIds() {
        Reservation reservation = new Reservation("Owens", 3, emptyList);
        Reservation reservation1 = new Reservation("Owens", 3, emptyList);
        planner.add(reservation);
        planner.add(reservation1);
        assertEquals(1, planner.reservationCount());
    }

    @Test
    public void plannerReportsReservationTotal() {
        Reservation reservation = new Reservation("Owens", 3, emptyList);
        Reservation reservation1 = new Reservation("Smith", 3, emptyList);
        planner.add(reservation);
        planner.add(reservation1);
        assertEquals(2, planner.reservationCount());
        assertEquals(6, planner.reservationTotal());
    }

    @Test(expected = InsufficientSeatingCapacity.class)
    public void shouldThrowExceptionWhenInsufficientCapacityForAllReservations() {
        Table table = new Table("A", 10);
        planner.add(table);
        Reservation reservation = new Reservation("Owens", 13, emptyList);
        planner.add(reservation);
        planner.plan();
    }

    @Test
    public void shouldAssignReservationsToTable() {
        Table table = new Table("A", 10);
        planner.add(table);
        Reservation reservation = new Reservation("Owens", 3, emptyList);
        Reservation reservation1 = new Reservation("Smith", 3, emptyList);
        planner.add(reservation);
        planner.add(reservation1);
        planner.plan();
        assertEquals(4, planner.tableRemainingCapacity());
    }

    @Test
    public void shouldAssignReservationsAcrossTables() {
        Table table = new Table("A", 5);
        Table table1 = new Table("B", 4);
        planner.add(table);
        planner.add(table1);
        Reservation reservation = new Reservation("Owens", 3, emptyList);
        Reservation reservation1 = new Reservation("Smith", 3, emptyList);
        planner.add(reservation);
        planner.add(reservation1);
        planner.plan();
        assertEquals(3, planner.tableRemainingCapacity());
        assertEquals(2, table.remainingCapacity());
        assertEquals(1, table1.remainingCapacity());
    }

    @Test(expected = InsufficientSeatingCapacity.class)
    public void shouldThrowExceptionWhenSufficientCapacityForAllReservationsButAllReservationAreNotAllocated() {
        Table table = new Table("A", 4);
        Table table1 = new Table("B", 2);
        planner.add(table);
        planner.add(table1);
        Reservation reservation = new Reservation("Owens", 3, emptyList);
        Reservation reservation1 = new Reservation("Smith", 3, emptyList);
        planner.add(reservation);
        planner.add(reservation1);
        planner.plan();
    }

    @Test
    public void testMatchingAlgorithm1() {
        List<String> andrewsConstraints = new ArrayList<String>() {{
            add("Boeing");
            add("Catalan");
        }};

        List<String> boeingConstraints = new ArrayList<String>() {{
            add("Catalan");
        }};

        Reservation andrews = new Reservation("Andrews", 3, andrewsConstraints);
        Reservation boeing = new Reservation("Boeing", 3, boeingConstraints);
        Reservation catalan = new Reservation("Catalan", 3, emptyList);

        planner.add(catalan);
        planner.add(boeing);
        planner.add(andrews);

        Table table = new Table("A", 12);
        planner.add(table);

        planner.plan();
        assertEquals(3, planner.tableRemainingCapacity());
        assertEquals(3, table.remainingCapacity());
    }

    @Test
    public void testMatchingAlgorithm2() {
        List<String> andrewsConstraints = new ArrayList<String>() {{
            add("Boeing");
            add("Catalan");
        }};

        List<String> boeingConstraints = new ArrayList<String>() {{
            add("Catalan");
        }};

        Reservation andrews = new Reservation("Andrews", 3, andrewsConstraints);
        Reservation boeing = new Reservation("Boeing", 3, boeingConstraints);
        Reservation catalan = new Reservation("Catalan", 3, emptyList);

        planner.add(catalan);
        planner.add(boeing);
        planner.add(andrews);

        Table table = new Table("A", 12);
        Table table1 = new Table("B", 12);
        planner.add(table);
        planner.add(table1);

        planner.plan();
        assertEquals(15, planner.tableRemainingCapacity());
        assertEquals(9, table.remainingCapacity());
        assertEquals(6, table1.remainingCapacity());

    }

    @Test
    public void testMatchingAlgorithm3() {
        List<String> andrewsConstraints = new ArrayList<String>() {{
            add("Boeing");
            add("Catalan");
        }};

        List<String> boeingConstraints = new ArrayList<String>() {{
            add("Catalan");
        }};

        Reservation andrews = new Reservation("Andrews", 3, andrewsConstraints);
        Reservation boeing = new Reservation("Boeing", 3, boeingConstraints);
        Reservation catalan = new Reservation("Catalan", 3, emptyList);

        planner.add(catalan);
        planner.add(boeing);
        planner.add(andrews);

        Table table = new Table("A", 12);
        Table table1 = new Table("B", 12);
        Table table2 = new Table("C", 12);
        planner.add(table);
        planner.add(table1);
        planner.add(table2);

        planner.plan();
        assertEquals(27, planner.tableRemainingCapacity());
        assertEquals(9, table.remainingCapacity());
        assertEquals(9, table1.remainingCapacity());
        assertEquals(9, table2.remainingCapacity());
    }

    @Test
    public void testMatchingAlgorithm4() {
        List<String> andrewsConstraints = new ArrayList<String>() {{
            add("Boeing");
            add("Catalan");
        }};

        List<String> boeingConstraints = new ArrayList<String>() {{
            add("Catalan");
        }};

        Reservation andrews = new Reservation("Andrews", 3, andrewsConstraints);
        Reservation boeing = new Reservation("Boeing", 4, boeingConstraints);
        Reservation catalan = new Reservation("Catalan", 3, emptyList);

        planner.add(catalan);
        planner.add(boeing);
        planner.add(andrews);

        Table table = new Table("A", 12);
        Table table1 = new Table("B", 12);
        Table table2 = new Table("C", 12);
        planner.add(table);
        planner.add(table1);
        planner.add(table2);

        planner.plan();
        assertEquals(26, planner.tableRemainingCapacity());
        assertEquals(9, table.remainingCapacity());
        assertEquals(9, table1.remainingCapacity());
        assertEquals(8, table2.remainingCapacity());
    }

    @Test
    public void exampleFromReadMe() {
        Table a = new Table("A", 8);
        Table b = new Table("B", 8);
        Table c = new Table("C", 7);
        Table d = new Table("D", 7);
        planner.add(a);
        planner.add(b);
        planner.add(c);
        planner.add(d);

        Reservation thornton = new Reservation("Thornton", 3, emptyList);
        Reservation garcia = new Reservation("Garcia", 2, emptyList);
        List<String> owensConstraints = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
        Reservation owens = new Reservation("Owens", 6, owensConstraints);
        List<String> smithConstraints = new ArrayList<String>() {{
            add("Garcia");
        }};
        Reservation smith = new Reservation("Smith", 1, smithConstraints);
        Reservation taylor = new Reservation("Taylor", 5, emptyList);
        Reservation reese = new Reservation("Reese", 7, emptyList);
        planner.add(thornton);
        planner.add(garcia);
        planner.add(owens);
        planner.add(smith);
        planner.add(taylor);
        planner.add(reese);

        planner.plan();

        assertEquals(6, planner.tableRemainingCapacity());
        assertEquals(6, a.remainingCapacity());
        assertEquals(0, b.remainingCapacity());
        assertEquals(0, c.remainingCapacity());
        assertEquals(0, d.remainingCapacity());
    }
}
