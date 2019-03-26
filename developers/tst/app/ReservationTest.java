package app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservationTest {

    @Test
    public void createReservation() {
        List<String> list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
        Reservation reservation = new Reservation("Owens", 3, list);
        assertTrue(reservation instanceof Reservation);
    }

    @Test
    public void reservationHasCorrectSize(){
        List<String> list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
        Reservation reservation = new Reservation("Owens", 3, list);
        assertEquals(3,reservation.getSize());
    }
}
