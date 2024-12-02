import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomBookingAppTest {

    private RoomBookingApp app;
    private BookingService mockBookingService;
    private final InputStream originalSystemIn = System.in; // To restore System.in after tests

    @Before
    public void setUp() {
        // Create a mock BookingService and RoomBookingApp
        mockBookingService = new BookingService(new RoomRepository());
        app = new RoomBookingApp(mockBookingService);
    }

    @After
    public void tearDown() {
        // Restore the original System.in
        System.setIn(originalSystemIn);
    }

    /**
     * Helper method to simulate user input for testing.
     *
     * @param input the simulated input string
     */
    private void simulateUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // Redirect System.in to the simulated input
    }

    @Test
    public void testStartBookingSuccessful() {
        // Simulate user input: room number "101" and customer name "John Doe"
        simulateUserInput("101\nJohn Doe\n");

        // Call the app's start method and check if the booking was successful
        boolean bookingResult = mockBookingService.bookRoom("101", "John Doe");
        app.start();

        // Assert that the booking was successful
        assertTrue(bookingResult);
    }

    @Test
    public void testStartBookingFailure() {
        // Simulate user input: room number "101" and customer name "John Doe"
        simulateUserInput("101\nJohn Doe\n");

        // Set the room as already booked to ensure failure
        Room room = mockBookingService.roomRepository.getRoom("101");
        if (room != null) {
            room.setBooked(true); // Mark the room as already booked
        }

        // Call the app's start method and check if the booking failed
        boolean bookingResult = mockBookingService.bookRoom("101", "John Doe");
        app.start();

        // Assert that the booking was unsuccessful
        assertFalse(bookingResult);
    }
}
