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
        user.setPassword("Password0968");
        assertTrue(user.verifyPassword("Password0968"));
        assertFalse(user.verifyPassword("Password123"));
    }

}