package app;

import java.util.ArrayList;
import java.util.List;

class Table implements Comparable<Table> {

    private final String id;
    private final int capacity;
    private final List<Reservation> reservations = new ArrayList<>();

    Table(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    String id() {
        return id;
    }

    List<Reservation> reservations() {
        return reservations;
    }

    boolean add(Reservation reservation) {
        if (tableHasCapacityFor(reservation))
            return reservations.add(reservation);
        return false;
    }

    private boolean tableHasCapacityFor(Reservation reservation) {
        return reservation.size() <= remainingCapacity();
    }

    int remainingCapacity() {
        int currentTotal = reservations.stream().mapToInt(Reservation::size).sum();
        return capacity - currentTotal;
    }

    @Override
    public int compareTo(Table that) {
        if (this.id.compareTo(that.id) != 0)
            if (this.remainingCapacity() <= that.remainingCapacity())
                return -1;
            else
                return 1;
        return 0;
    }
}
