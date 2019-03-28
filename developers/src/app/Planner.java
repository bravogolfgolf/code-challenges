package app;

import entity.Reservation;
import entity.Table;
import ui.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Planner {
    private final Set<Table> tables = new TreeSet<>();
    private final Set<Reservation> reservations = new TreeSet<>();
    private final Unconstrained unConstrained = new Unconstrained();
    private final Constrained constrained = new Constrained();
    private final Presenter presenter;

    Planner(Presenter presenter) {
        this.presenter = presenter;
    }

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
        return reservations.stream().mapToInt(Reservation::size).sum();
    }

    void plan() {

        if (tableRemainingCapacity() < reservationTotal()) insufficientCapacity();

        List<Reservation> unmatched = new ArrayList<>(reservations);
        if (!constrained.allocate(unmatched, tables))
            if (!unConstrained.allocate(unmatched, tables))
                insufficientCapacity();
    }

    private void insufficientCapacity() {
        throw new InsufficientSeatingCapacity();
    }

    String display() {
        return presenter.present(tables);
    }

    class InsufficientSeatingCapacity extends RuntimeException {
    }
}
