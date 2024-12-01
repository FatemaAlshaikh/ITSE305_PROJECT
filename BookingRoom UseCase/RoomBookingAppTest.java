import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RoomBookingAppTest {

    private RoomBookingApp app;
    private BookingService mockBookingService;
    private final InputStream originalSystemIn = System.in;  // To restore System.in after test

    @Before
    public void setUp() {
        // Mock the BookingService
        // Using a spy to observe real behavior
        app = new RoomBookingApp(mockBookingService);
    }

    @Test
    public void testStartBookingSuccessful() {
        String input = "101\nJohn Doe\n";  // Simulating user input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Set the input stream to simulate user input

        // Call the method, which should invoke the actual logic in BookingService
        boolean bookingResult = mockBookingService.bookRoom("101", "John Doe");
        app.start();  // Start the application logic

        // Now we check the state directly
        assertTrue(bookingResult);  // Assert that the booking was successful

        // Optionally, you can assert if a specific state in the mock has changed or if an output was printed
        // If the app writes to the console, capture the output and check it
        // For example, you could capture System.out and assert if it contains "Booking successful" or similar
        // Using an ArgumentCaptor or other means to inspect internal logic can be done here

        // Restore the original System.in
        System.setIn(originalSystemIn);
    }

    @Test
    public void testStartBookingFailure() {
        String input = "101\nJohn Doe\n";  // Simulating user input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);  // Set the input stream to simulate user input

        // Call the method directly on the mock (no stubbing with `when`)
        boolean bookingResult = mockBookingService.bookRoom("101", "John Doe");
        app.start();  // Start the application logic

        // Check the result directly
        assertFalse(bookingResult);  // Assert that booking was unsuccessful

        // Optionally, check if an output like "Booking failed" was printed
        // You can use a similar approach to capture System.out if needed

        // Restore the original System.in
        System.setIn(originalSystemIn);
    }
}
