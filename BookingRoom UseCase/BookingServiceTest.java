import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookingServiceTest {




    @Test
    public void testBookRoomSuccessfully() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();  // Using real RoomRepository
        Room room = roomRepository.getRoom("101");  // Getting a real room from the repository
        BookingService bookingService = new BookingService(roomRepository);

        // Act
        boolean result = bookingService.bookRoom("101", "John Doe");

        // Assert
        assertTrue(result); // Booking should be successful
        assertTrue(room.isBooked()); // Room should now be marked as booked
    }

    @Test
    public void testBookRoomFailDueToRoomAlreadyBooked() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();  // Using real RoomRepository
        Room room = roomRepository.getRoom("101");
        room.setBooked(true);  // Manually set the room as already booked
        BookingService bookingService = new BookingService(roomRepository);

        // Act
        boolean result = bookingService.bookRoom("101", "John Doe");

        // Assert
        assertFalse(result); // Booking should fail because the room is already booked
    }

    @Test
    public void testBookRoomFailDueToRoomNotFound() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();  // Using real RoomRepository
        BookingService bookingService = new BookingService(roomRepository);

        // Act
        boolean result = bookingService.bookRoom("999", "John Doe");

        // Assert
        assertFalse(result); // Booking should fail because the room does not exist
    }
}
