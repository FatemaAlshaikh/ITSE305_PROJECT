import org.junit.Test;

import static org.junit.Assert.*;

public class RoomRepositoryTest {

    @Test
    public void testGetRoomExistingRoom() {
        // Arrange
        RoomRepository repository = new RoomRepository();

        // Act
        Room room = repository.getRoom("101");

        // Assert
        assertNotNull(room);
        assertEquals("101", room.getRoomNumber());
    }

    @Test
    public void testGetRoomNonExistingRoom() {
        // Arrange
        RoomRepository repository = new RoomRepository();

        // Act
        Room room = repository.getRoom("999");

        // Assert
        assertNull(room);
    }

    @Test
    public void testInitializeRooms() {
        // Arrange
        RoomRepository repository = new RoomRepository();

        // Act
        Room room101 = repository.getRoom("101");
        Room room102 = repository.getRoom("102");
        Room room103 = repository.getRoom("103");

        // Assert
        assertNotNull(room101);
        assertNotNull(room102);
        assertNotNull(room103);
    }
}
