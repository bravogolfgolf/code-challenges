package app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlannerTest {

    @Test
    public void createPlanner() {
        Planner planner = new Planner();
        assertTrue(planner instanceof Planner);
    }

    @Test
    public void plannersAcceptTables() {
        Planner planner = new Planner();
        Table table = new Table("A", 10);
        planner.add(table);
        assertEquals(1, planner.tableCount());
    }
}
