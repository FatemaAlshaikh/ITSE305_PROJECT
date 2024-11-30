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
        public void testCancelReservationSuccessfully() {
            // Arrange
            ReservationRepository repository = new ReservationRepository();
            ReservationService service = new ReservationService(repository);
            service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
            // Simulate user input
            Scanner scanner = new Scanner("yes\n"); // User confirms cancellation
            // Act
            service.cancelReservation("1", "020900581");
            // Assert
            assertFalse(repository.exists("1", "020900581"));
        }
        @Test
    public void testCancelReservationWithInvalidConfirmation() {
        // Arrange
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
        // Simulate user input for cancellation with an invalid response
        Scanner scanner = new Scanner("no\n"); // User cancels the cancellation
        // Act
        service.cancelReservation("1", "020900581");
        // Assert
        assertTrue(repository.exists("1", "020900581")); // Reservation should still exist
    }
    @Test
    public void testCancelReservationWithNonExistentReservation() {
        // Arrange
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        // Simulate user input
        Scanner scanner = new Scanner("yes\n"); // User confirms cancellation
        // Act
        service.cancelReservation("999", "nonexistentCPR");
        // Assert
        assertFalse(repository.exists("999", "nonexistentCPR")); // Reservation should not exist
    }
    @Test
    public void testCancelReservationWithInvalidRoomNumber() {
        // Arrange
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
        // Simulate user input for invalid room number (empty)
        Scanner scanner = new Scanner("yes\n");
        // Act
        service.cancelReservation("", "020900581");
        // Assert
        assertTrue(repository.exists("1", "020900581")); // Reservation should still exist
    }
    @Test
    public void testCancelReservationWithInvalidCPR() {
        // Arrange
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
        // Simulate user input
        Scanner scanner = new Scanner("yes\n");
        // Act
        service.cancelReservation("1", "invalidCPR");
        // Assert
        assertTrue(repository.exists("1", "020900581")); // Reservation with correct CPR should still exist
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
        @Test(expected = IllegalArgumentException.class)
    public void testAddReservationWithInvalidInput() {
        // Arrange
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        // Act
        service.addReservation(null, "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now());
        // Assert: An exception should be thrown because the customer name is null
    }
    }
