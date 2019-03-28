package entity;

import java.util.ArrayList;
import java.util.List;

public class Table implements Comparable<Table> {

    private final String id;
    private final int capacity;
    private final List<Reservation> reservations = new ArrayList<>();

    public Table(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String id() {
        return id;
    }

    public List<Reservation> reservations() {
        return reservations;
    }

    public boolean add(Reservation reservation) {
        if (hasCapacityFor(reservation))
            return reservations.add(reservation);
        return false;
    }

    public boolean hasCapacityFor(Reservation reservation) {
        return reservation.size() <= remainingCapacity();
    }

    public int remainingCapacity() {
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
