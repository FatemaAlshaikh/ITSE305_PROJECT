import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class RoomTest {

    @Test
    public void testRoomCreation() {
        // Arrange
        Room room = new Room("101");

        // Act & Assert
        assertEquals("101", room.getRoomNumber());
        assertFalse(room.isBooked());
    }

    @Test
    public void testSetBooked() {
        // Arrange
        Room room = new Room("101");

        // Act
        room.setBooked(true);

        // Assert
        assertTrue(room.isBooked());
    }

    @Test
    public void testSetCustomerName() {
        // Arrange
        Room room = new Room("101");

        // Act
        room.setCustomerName("John Doe");

        // No assertion needed, since we don't have a getter for customerName in the provided code,
        // but if there were a getter, we would assert its value here.
    }
}
