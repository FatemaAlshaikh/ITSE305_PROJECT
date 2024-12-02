import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        // Initialize the Room object before each test
        room = new Room("101");
    }

    @Test
    public void testRoomCreation() {
        // Act & Assert
        assertEquals("101", room.getRoomNumber());
        assertFalse(room.isBooked());
    }

    @Test
    public void testSetBooked() {
        // Act
        room.setBooked(true);

        // Assert
        assertTrue(room.isBooked());
    }

    @Test
    public void testSetCustomerName() {
        // Act
        room.setCustomerName("John Doe");

        // Assert
        assertEquals("John Doe", room.getCustomerName());
    }
}

