import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class CancelBookingTest {

        @Test
        public void testDisplayReservations() {
            ReservationRepository repository = new ReservationRepository();
            ReservationService service = new ReservationService(repository);
            service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            CancelBooking.displayReservations(service);
            String output = outputStream.toString();
            assertTrue(output.contains("Current Reservations"));
            assertTrue(output.contains("Fatema Alshaikh"));
            assertTrue(output.contains("Deluxe"));
            assertTrue(output.contains("Room Number: 1"));
    }
    @Test
    public void testDisplayReservationsWithNoReservations() {
        // Arrange
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        // Act
        CancelBooking.displayReservations(service);
        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("No current reservations."));
    }
    @Test
    public void testDisplayReservationsWithMultipleReservations() {
        // Arrange
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
        service.addReservation("Zahra Husan", "Standard", "2", 100.0, "010400357", "00973-33367225", LocalDate.now());
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        // Act
        CancelBooking.displayReservations(service);
        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Current Reservations"));
        assertTrue(output.contains("Fatema Alshaikh"));
        assertTrue(output.contains("Zahra Husan"));
        assertTrue(output.contains("Room Number: 1"));
        assertTrue(output.contains("Room Number: 2"));
    
        }
    }
