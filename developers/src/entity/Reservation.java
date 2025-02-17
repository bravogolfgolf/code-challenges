package entity;

import java.util.List;

public class Reservation implements Comparable<Reservation> {
    private final String id;
    private final int size;
    private final List<String> constraints;

    public Reservation(String id, int size, List<String> constraints) {
        this.id = id;
        this.size = size;
        this.constraints = constraints;
    }

    public String id() {
        return id;
    }

    public int size() {
        return size;
    }

    public boolean dislikes(Reservation reservation) {
        return constraints.contains(reservation.id);
    }

    @Override
    public int compareTo(Reservation that) {
        if (this.id.compareTo(that.id) != 0)
            if (this.size > that.size)
                return -1;
            else if (this.size < that.size)
                return 1;
            else if (this.constraints.size() > that.constraints.size())
                return -1;
            else
                return 1;
        else return 0;
    }
}
