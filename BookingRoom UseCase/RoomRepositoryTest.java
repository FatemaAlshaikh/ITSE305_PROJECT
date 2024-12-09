import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomRepositoryTest {

    private RoomRepository repository;

    @Before
    public void setUp() {
        // Initialize the repository before each test
        repository = new RoomRepository();
    }

    /**
     * Helper method to validate a room.
     *
     * @param room       The room object to validate.
     * @param roomNumber The expected room number.
     * @param isBooked   The expected booking status.
     */
    private void validateRoom(Room room, String roomNumber, boolean isBooked) {
        assertNotNull("Room should not be null", room);
        assertEquals("Room number mismatch", roomNumber, room.getRoomNumber());
        assertEquals("Booking status mismatch", isBooked, room.isBooked());
    }

    @Test
    public void testGetExistingRoomSuccessfully() {
        // Act
        Room room = repository.getRoom("101");

        // Assert
        validateRoom(room, "101", false);
    }

    @Test
    public void testGetNonExistingRoomReturnsNull() {
        // Act
        Room room = repository.getRoom("999");

        // Assert
        assertNull("Room should be null for non-existing room number", room);
    }

    @Test
    public void testGetMultipleRoomsSuccessfully() {
        // Act
        Room room101 = repository.getRoom("101");
        Room room102 = repository.getRoom("102");
        Room room103 = repository.getRoom("103");

        // Assert
        validateRoom(room101, "101", false);
        validateRoom(room102, "102", false);
        validateRoom(room103, "103", false);
    }
}
