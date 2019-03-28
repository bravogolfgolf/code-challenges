package app;

import entity.Reservation;
import entity.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class Constrained {

    boolean allocate(List<Reservation> unmatched, Set<Table> tables) {
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

        if (unmatched.size() == 0)
            return true;

        return allocate(unmatched, tables);
    }
}