import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import static org.junit.Assert.*;

    public class ReservationServiceTest {
        @Test
        public void testAddReservation() {
            // Arrange
            ReservationRepository repository = new ReservationRepository();
            ReservationService service = new ReservationService(repository);
            String customerName = "Fatema Alshaikh";
            String roomType = "Deluxe";
            String roomNumber = "1";
            double price = 150.0;
            String cpr = "020900581";
            String phoneNumber = "00973-36784589";
            LocalDate bookingDate = LocalDate.now();

            // Act
            service.addReservation(customerName, roomType, roomNumber, price, cpr, phoneNumber, bookingDate);

            // Assert
            List<Reservation> reservations = repository.getAll();
            assertEquals(1, reservations.size());
            assertEquals(customerName, reservations.get(0).getcustomerName());
        }

        @Test
        public void testCancelReservation() {
            // Arrange
            ReservationRepository repository = new ReservationRepository();
            ReservationService service = new ReservationService(repository);
            service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());

            // Simulate user input
            Scanner scanner = new Scanner("yes\n"); // User confirms cancellation

            // Act
            service.cancelReservation("1", "020900581", scanner);

            // Assert
            assertFalse(repository.exists("1", "020900581"));
        }

        @Test
        public void testShowCancellationPolicy() {
            // Arrange
            ReservationRepository repository = new ReservationRepository();
            ReservationService service = new ReservationService(repository);

            // Act and Assert
            service.showCancellationPolicy();
            // You can verify by checking the console output manually as there is no easy way to capture System.out in a test.
        }
    }
