import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class ReservationTest {
    @Test
    public void testReservationConstructor() {
        LocalDate bookingDate = LocalDate.now();
        Reservation reservation = new Reservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", bookingDate);

        assertEquals("Fatema Alshaikh", reservation.getcustomerName());
        assertEquals("Deluxe", reservation.getroomType());
        assertEquals("1", reservation.getRoomNumber());
        assertEquals(150.0, reservation.getprice(), 0.01);
        assertEquals("020900581", reservation.getCpr());
        assertEquals("00973-36784589", reservation.getphoneNumber());
        assertEquals(bookingDate, reservation.getBookingDate());
    }

    @Test
    public void testToString() {
        LocalDate bookingDate = LocalDate.now();
        Reservation reservation = new Reservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", bookingDate);
        String expectedString = "Customer Name: Fatema Alshaikh, Room Type: Deluxe, Room Number: 1, Price: $150.0, CPR: 020900581, Phone Number: 00973-36784589, Booking Date: " + bookingDate;
        assertEquals(expectedString, reservation.toString());
    }
    // Test for edge case: null or empty values in constructor
    @Test(expected = IllegalArgumentException.class)
    public void testReservationConstructorWithNullValues() {
        // Null customer name should throw an exception (assuming validation is done in the constructor)
        LocalDate bookingDate = LocalDate.now();
        new Reservation(null, "Deluxe", "1", 150.0, "020900581", "00973-36784589", bookingDate);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testReservationConstructorWithEmptyValues() {
        // Empty customer name should throw an exception (assuming validation is done in the constructor)
        LocalDate bookingDate = LocalDate.now();
        new Reservation("", "Deluxe", "1", 150.0, "020900581", "00973-36784589", bookingDate);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testReservationConstructorWithInvalidPrice() {
        // Negative price should throw an exception
        LocalDate bookingDate = LocalDate.now();
        new Reservation("Fatema Alshaikh", "Deluxe", "1", -150.0, "020900581", "00973-36784589", bookingDate);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testReservationConstructorWithInvalidPhoneNumber() {
        // Invalid phone number (assuming valid phone number format is checked)
        LocalDate bookingDate = LocalDate.now();
        new Reservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "invalid-phone", bookingDate);
    }
}

