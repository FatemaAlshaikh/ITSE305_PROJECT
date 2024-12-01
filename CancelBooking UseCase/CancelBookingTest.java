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
        }
    }
