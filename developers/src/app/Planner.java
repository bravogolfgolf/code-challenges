package app;

import java.util.Set;
import java.util.TreeSet;

class Planner {

    private Set<Table> tables = new TreeSet<>();
    private Set<Reservation> reservations = new TreeSet<>();

    void add(Table table) {
        tables.add(table);
    }

    void add(Reservation reservation) {
        reservations.add(reservation);
    }

    int tableCount() {
        return tables.size();
    }

    int tableRemainingCapacity() {
        return tables.stream().mapToInt(Table::remainingCapacity).sum();
    }

    int reservationCount() {
        return reservations.size();
    }

    int reservationTotal() {
        return reservations.stream().mapToInt(Reservation::getSize).sum();
    }

    void plan() {
        if (tableRemainingCapacity() < reservationTotal()) insufficientCapacity();
        boolean allReservationsAllocated = unconstrainedAllocation();
        if (!allReservationsAllocated) insufficientCapacity();
    }

    private void insufficientCapacity() {
        throw new InsufficientSeatingCapacity();
    }

    private boolean unconstrainedAllocation() {
        boolean success = false;
        for (Reservation reservation : reservations) {
            for (Table table : tables) {
                success = table.add(reservation);
                if (success) break;
            }
        }
        return success;
    }

    class InsufficientSeatingCapacity extends RuntimeException {
    }
}
