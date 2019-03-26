package app;

import java.util.HashSet;
import java.util.Set;

public class Planner {

    Set<Table> tables = new HashSet<>();
    Set<Reservation> reservations = new HashSet<>();

    public void add(Table table) {
        tables.add(table);
    }

    public void add(Reservation reservation) {
        reservations.add(reservation);
    }

    public int tableCount() {
        return tables.size();
    }

    public int tableCapacity() {
        return tables.stream().mapToInt(Table::getCapacity).sum();
    }

    public int reservationCount() {
        return reservations.size();
    }

    public int reservationTotal() {
        return reservations.stream().mapToInt(Reservation::getSize).sum();
    }
}
