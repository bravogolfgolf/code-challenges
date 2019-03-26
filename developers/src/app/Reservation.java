package app;

import java.util.List;

public class Reservation {
    private final String id;
    private final int number;
    private final List<String> constraints;

    public Reservation(String id, int number, List<String> constraints) {
        this.id = id;
        this.number = number;
        this.constraints = constraints;
    }
}
