package app;

import entity.Reservation;
import entity.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class Constrained {

    boolean allocate(List<Reservation> unmatched, Set<Table> tables) {
        List<Reservation> toBeMatched = new ArrayList<>(unmatched);

        HashMap<Table, List<Reservation>> potentialMatches = new HashMap<>();
        for (Table table : tables) {
            potentialMatches.put(table, new ArrayList<>());
        }

        for (Reservation potential : toBeMatched) {
            for (Table table : tables) {
                if (table.hasCapacityFor(potential)) {
                    if (table.hasReservations()) {
                        boolean conflict = false;
                        for (Reservation existing : table.reservations()) {
                            conflict = existing.dislike(potential) || potential.dislike(existing);
                            if (conflict) break;
                        }

                        if (!conflict) {
                            unmatched.remove(potential);
                            potentialMatches.get(table).add(potential);
                            break;
                        }
                    } else {
                        unmatched.remove(potential);
                        potentialMatches.get(table).add(potential);
                        break;
                    }
                }
            }
        }

        if (unmatched.size() > 0)
            return false;

        for (Table table : potentialMatches.keySet()) {
            List<Reservation> list = potentialMatches.get(table);
            if (list.size() >= 1) {
                Reservation reservation = list.get(0);
                table.add(reservation);
                list.remove(reservation);
                unmatched.addAll(list);
                list.clear();
            }
        }

        if (unmatched.size() == 0)
            return true;

        return allocate(unmatched, tables);
    }
}