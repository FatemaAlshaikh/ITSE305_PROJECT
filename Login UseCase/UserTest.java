import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User("test_user", "test_password");
        assertEquals("test_user", user.getUsername());
        assertEquals("test_password", user.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User("initial_user", "initial_password");
        user.setUsername("updated_user");
        user.setPassword("updated_password");
        assertEquals("updated_user", user.getUsername());
        assertEquals("updated_password", user.getPassword());
    }
}