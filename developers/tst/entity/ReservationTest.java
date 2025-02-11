package entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservationTest {

    private Reservation reservation;

    @Before
    public void setUp() {
        List<String> list = new ArrayList<String>() {{
            add("Thornton");
            add("Taylor");
        }};
        reservation = new Reservation("Owens", 3, list);
    }

    @Test
    public void shouldReturnValueSetToConstructor() {
        assertEquals("Owens", reservation.id());
    }

    @Test
    public void shouldReturnSizeSetToConstructor() {
        assertEquals(3, reservation.size());
    }

    @Test
    public void shouldReturnTrueIfDislike() {
        assertTrue(reservation.dislikes(new Reservation("Thornton", 3, null)));
    }
}
