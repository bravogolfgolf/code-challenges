package app;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TableTest {

    @Test
    public void createsTable() {
        Table table = new Table("A", 10);
        assertTrue(table instanceof Table);
    }
}
