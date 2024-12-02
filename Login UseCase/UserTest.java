import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void testPasswordHashingAndVerification(){
        User user = new User("test_user", "password790");

        // verify password matches after hashing
        assertTrue(user.verifyPassword("password790"));

        // ensure an incorrect password doesn’t match
        assertFalse(user.verifyPassword("password001"));
    }
    
    @Test
    public void testPasswordUpdateAndVerification(){
        User user = new User("test_user", "Password123");

        // update password and verify
        user.setPassword("userPassword");
        assertTrue(user.verifyPassword("userPassword"));
        assertFalse(user.verifyPassword("Password123"));
    }

    @Test
    public void testEqualsAndHashCode() {
    User user1 = new User("test_user", "password4564");
    User user2 = new User("test_user", "Password09878");
    assertEquals(user1, user2); // Same username, should be equal
    assertEquals(user1.hashCode(), user2.hashCode());
}
}