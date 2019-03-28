package ui;

import entity.Table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Presenter {
    public String present(Set<Table> set) {
        List<Table> list = new ArrayList<>(set);
        list.sort(Comparator.comparing(Table::id));

        StringBuilder result = new StringBuilder();
        for (Table table : list) {
            result.append(String.format("Table %s: ", table.id()));

            if (table.reservations().size() > 0)
                result.append(String.format("%s, party of %d", table.reservations().get(0).id(), table.reservations().get(0).size()));
            if (table.reservations().size() > 1)
                for (int i = 1; i < table.reservations().size(); i++) {
                    result.append(String.format(" & %s, party of %d", table.reservations().get(i).id(), table.reservations().get(i).size()));
                }
            result.append("\n");
        }
        return result.toString();
    }
}
