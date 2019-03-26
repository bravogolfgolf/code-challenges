package app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservationTest {

    private List<String> list;
    private Reservation reservation;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
        reservation = new Reservation("Owens", 3, list);
    }

    @Test
    public void createReservation() {
        assertTrue(reservation instanceof Reservation);
    }

    @Test
    public void reservationHasCorrectSize() {
        assertEquals(3, reservation.getSize());
    }
}
