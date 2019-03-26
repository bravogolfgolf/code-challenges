package app;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private final String id;
    private final int capacity;
    private final List<Reservation> reservations = new ArrayList<>();

    public Table(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public boolean add(Reservation reservation) {
        int currentTotal = reservations.stream().mapToInt(r -> r.getSize()).sum();
        int remainingCapacity = capacity - currentTotal;
        if (reservation.getSize() < remainingCapacity)
            return reservations.add(reservation);
        return false;
    }
}
