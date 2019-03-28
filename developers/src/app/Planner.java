package app;

import entity.Reservation;
import entity.Table;
import ui.Presenter;

import java.util.*;

class Planner {
    private final Set<Table> tables = new TreeSet<>();
    private final Set<Reservation> reservations = new TreeSet<>();
    private final List<Reservation> unmatched = new ArrayList<>();
    private Presenter presenter;

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

        if (!constrainedAllocation())
            if (!unconstrainedAllocation())
                insufficientCapacity();
    }

    private void insufficientCapacity() {
        throw new InsufficientSeatingCapacity();
    }

    private boolean unconstrainedAllocation() {
        boolean success = false;
        for (Reservation reservation : unmatched) {
            for (Table table : tables) {
                success = table.add(reservation);
                if (success) break;
            }
        }
        return success;
    }

    private boolean constrainedAllocation() {
        unmatched.addAll(reservations);
        return constrainedAllocation(unmatched);
    }

    private boolean constrainedAllocation(List<Reservation> unmatched) {
        if (unmatched.size() == 0)
            return true;

        List<Reservation> reservations = new ArrayList<>(unmatched);

        HashMap<Table, List<Reservation>> map = new HashMap<>();
        for (Table table : tables) {
            map.put(table, new ArrayList<>());
        }

        for (Reservation reservation : reservations) {
            for (Table table : tables) {
                if (reservation.size() <= table.remainingCapacity()) {
                    if (table.reservations().size() >= 1) {
                        boolean result = false;
                        for (Reservation res : table.reservations()) {
                            result = res.dislike(reservation.id()) || reservation.dislike(res.id());
                            if (result) break;
                        }

                        if (!result) {
                            unmatched.remove(reservation);
                            map.get(table).add(reservation);
                            break;
                        }
                    } else {
                        unmatched.remove(reservation);
                        map.get(table).add(reservation);
                        break;
                    }
                }
            }
        }

        if (unmatched.size() > 0)
            return false;

        for (Table table : map.keySet()) {
            List<Reservation> list = map.get(table);
            if (list.size() >= 1) {
                Reservation reservation = list.get(0);
                table.add(reservation);
                list.remove(reservation);
                unmatched.addAll(list);
                list.clear();
            }
        }
        return constrainedAllocation(unmatched);
    }

    String display() {
        return presenter.present(tables);
    }

    class InsufficientSeatingCapacity extends RuntimeException {
    }
}
