package app;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
