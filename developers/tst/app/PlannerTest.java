package app;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlannerTest {

    @Test
    public void createPlanner() {
        Planner planner = new Planner();
        assertTrue(planner instanceof Planner);
    }
}
