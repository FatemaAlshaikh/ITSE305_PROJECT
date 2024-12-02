import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BookingServiceTest {

    private RoomRepository roomRepository;
    private BookingService bookingService;

    @Before
    public void setUp() {
        roomRepository = new RoomRepository();  // Initialize RoomRepository
        bookingService = new BookingService(roomRepository);  // Initialize BookingService with RoomRepository
    }

    @Test
    public void testBookRoomSuccessfully() {
        // Arrange
        Room room = roomRepository.getRoom("101");  // Getting a real room from the repository

        // Act
        boolean result = bookingService.bookRoom("101", "John Doe");

        // Assert
        assertTrue(result); // Booking should be successful
        assertTrue(room.isBooked()); // Room should now be marked as booked
    }

    @Test
    public void testBookRoomFailDueToRoomAlreadyBooked() {
        // Arrange
        Room room = roomRepository.getRoom("101");
        room.setBooked(true);  // Manually set the room as already booked

        // Act
        boolean result = bookingService.bookRoom("101", "John Doe");

        // Assert
        assertFalse(result); // Booking should fail because the room is already booked
    }

    @Test
    public void testBookRoomFailDueToRoomNotFound() {
        // Act
        boolean result = bookingService.bookRoom("999", "John Doe");

        // Assert
        assertFalse(result); // Booking should fail because the room does not exist
    }

    @Test
    public void testGetRoomThatDoesNotExist() {
        // Act
        Room room = roomRepository.getRoom("999");  // Attempting to get a non-existent room

        // Assert
        assertNull(room); // The room should be null because it does not exist
    }

    @Test
    public void testUnbookRoomFailDueToRoomNotFound() {
        // Act
        boolean result = bookingService.unbookRoom("999"); // Attempting to unbook a non-existent room

        // Assert
        assertFalse(result); // Unbooking should fail because the room does not exist
    }

    @Test
    public void testUnbookRoomSuccessfully() {
        // Arrange
        Room room = roomRepository.getRoom("101");
        room.setBooked(true); // Set the room as booked initially

        // Act
        boolean result = bookingService.unbookRoom("101"); // Attempting to unbook an existing room

        // Assert
        assertTrue(result); // Unbooking should be successful
        assertFalse(room.isBooked()); // Room should now be marked as not booked
    }
}
