package app;

import entity.Reservation;
import entity.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class Constrained {

    boolean allocate(List<Reservation> unmatchedReservations, Set<Table> tables) {
        List<Reservation> toBeMatched = new ArrayList<>(unmatchedReservations);

        HashMap<Table, List<Reservation>> matcher = new HashMap<>();
        for (Table table : tables) {
            matcher.put(table, new ArrayList<>());
        }

        for (Reservation potential : toBeMatched) {
            for (Table table : tables) {
                if (table.hasCapacityFor(potential)) {
                    if (table.hasReservations()) {
                        if (!conflictBetweenExistingReservationsAnd(potential, table)) {
                            unmatchedReservations.remove(potential);
                            matcher.get(table).add(potential);
                            break;
                        }

                    } else {
                        unmatchedReservations.remove(potential);
                        matcher.get(table).add(potential);
                        break;
                    }
                }
            }
        }

        if (has(unmatchedReservations))
            return false;

        for (Table table : matcher.keySet()) {
            List<Reservation> potentialMatches = matcher.get(table);
            if (has(potentialMatches)) {

                Reservation match = selectMatchFrom(potentialMatches);
                potentialMatches.remove(match);
                table.add(match);

                unmatchedReservations.addAll(potentialMatches);
            }
        }

        if (unmatchedReservations.size() == 0)
            return true;

        return allocate(unmatchedReservations, tables);
    }

    private boolean conflictBetweenExistingReservationsAnd(Reservation potential, Table table) {
        boolean conflict = false;
        for (Reservation existing : table.reservations()) {
            conflict = existing.dislikes(potential) || potential.dislikes(existing);
            if (conflict) break;
        }
        return conflict;
    }

    private boolean has(List<Reservation> list) {
        return list.size() > 0;
    }

    private Reservation selectMatchFrom(List<Reservation> list) {
        return list.get(0);
    }
}