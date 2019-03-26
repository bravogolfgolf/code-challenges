package app;

import java.util.ArrayList;
import java.util.List;

public class Planner {

    List<Table> tables = new ArrayList<>();

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
