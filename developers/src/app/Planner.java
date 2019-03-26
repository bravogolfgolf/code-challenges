package app;

import java.util.HashSet;
import java.util.Set;

public class Planner {

    Set<Table> tables = new HashSet<>();

    public void add(Table table) {
        tables.add(table);
    }

    public int tableCount() {
        return tables.size();
    }

    public int getTotalCapacity() {
        return tables.stream().mapToInt(Table::getCapacity).sum();
    }
}
