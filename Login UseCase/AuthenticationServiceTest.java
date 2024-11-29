import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class AuthenticationServiceTest {

    private UserRepository userRepository;
    private AuthenticationService authService;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
        authService = new AuthenticationService(userRepository);
        userRepository.addUser(new User("test_user", "password123"));
        userRepository.addUser(new User("admin", "adminPass"));
    }

    @Test
    public void testSuccessfulAuthentication() {
        assertTrue(authService.authenticate("test_user", "password123"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(authService.authenticate("test_user", "wrongPassword"));
    }

    @Test
    public void testNonExistentUser() {
        assertFalse(authService.authenticate("unknown_user", "anyPassword"));
    }

    @Test
    public void testEmptyInputs() {
        assertFalse(authService.authenticate("", ""));
    }

    @Test
    public void testNullInputs() {
        assertFalse(authService.authenticate(null, null));
    }
}
