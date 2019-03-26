package app;

import java.util.List;

public class Reservation {
    private final String id;
    private final int size;
    private final List<String> constraints;

    public Reservation(String id, int size, List<String> constraints) {
        this.id = id;
        this.size = size;
        this.constraints = constraints;
    }

    public int getSize() {
        return size;
    }
}
