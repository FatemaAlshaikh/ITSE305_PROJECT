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
}