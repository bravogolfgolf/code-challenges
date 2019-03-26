package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table {

    private final String id;
    private final int capacity;
    private final List<Reservation> reservations = new ArrayList<>();

    public Table(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public boolean add(Reservation reservation) {
        if (tableHasCapacityFor(reservation))
            return reservations.add(reservation);
        return false;
    }

    private boolean tableHasCapacityFor(Reservation reservation) {
        int currentTotal = reservations.stream().mapToInt(Reservation::getSize).sum();
        int remainingCapacity = capacity - currentTotal;
        return reservation.getSize() < remainingCapacity;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return id.equals(table.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
