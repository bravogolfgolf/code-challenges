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

    public int tableRemainingCapacity() {
        return tables.stream().mapToInt(Table::remainingCapacity).sum();
    }

    public int reservationCount() {
        return reservations.size();
    }

    public int reservationTotal() {
        return reservations.stream().mapToInt(Reservation::getSize).sum();
    }

    public void plan() {
        boolean success = false;
        for (Reservation reservation : reservations) {
            for (Table table : tables) {
                success = table.add(reservation);
                if (success) break;
            }
        }
        if (!success) throw new InsufficientSeatingCapacity();
    }

    class InsufficientSeatingCapacity extends RuntimeException {
    }
}
