import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserRepositoryTest {
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testAddAndFindUser() {
        User user = new User("test_user", "password123");
        assertTrue(userRepository.addUser(user)); // first add should be succeed
        userRepository.addUser(user);
        User retrievedUser = userRepository.findByUsername("test_user");
        assertNotNull(retrievedUser);
        assertEquals("test_user", retrievedUser.getUsername());
        assertEquals("password123", retrievedUser.getPassword());
    }

    @Test
    public void testFindNonExistentUser() {
        User retrievedUser = userRepository.findByUsername("nonexistent_user");
        assertNull(retrievedUser);
    }

    @Test
    public void testDuplicateUsers(){
        User user1 = new User("duplicate_user", "password123");
        User user2 = new User("duplicate_user", "password12345");

        assertTrue(userRepository.addUser(user1)); // first add should succeed
        assertFalse(userRepository.addUser(user2)); // second add with the same username should fail
    }
}