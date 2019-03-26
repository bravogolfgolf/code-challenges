package app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlannerTest {

    private Planner planner;

    @Before
    public void setUp() {
        planner = new Planner();
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
}
