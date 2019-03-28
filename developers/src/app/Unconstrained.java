package app;

import entity.Reservation;
import entity.Table;

import java.util.List;
import java.util.Set;

class Unconstrained {

    boolean allocate(List<Reservation> unmatched, Set<Table> tables) {
        boolean success = false;
        for (Reservation reservation : unmatched) {
            for (Table table : tables) {
                success = table.add(reservation);
                if (success) break;
            }
        }
        return success;
    }
}