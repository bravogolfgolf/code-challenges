package app;

import java.util.HashSet;
import java.util.Set;

public class Planner {

    Set<Table> tables = new HashSet<>();
    Set<Reservation> reservations = new HashSet<>();

    public void add(Table table) {
        tables.add(table);
    }

    public int tableCount() {
        return tables.size();
    }

    public int getTotalCapacity() {
        return tables.stream().mapToInt(Table::getCapacity).sum();
    }

    public void add(Reservation reservation) {
        reservations.add(reservation);
    }

    public int reservationCount() {
        return reservations.size();
    }

    public int totalReservations() {
        return reservations.stream().mapToInt(Reservation::getSize).sum();
    }
}
