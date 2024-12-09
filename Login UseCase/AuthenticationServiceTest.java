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

    @Test
    public void testDuplicateUsernames(){
        // attempt to add a user with a duplicate username
        boolean isAdded = userRepository.addUser(new User("test_user", "newPassword123"));

        // make sure duplicate username can’t be added
        assertFalse(isAdded);

        // verify the original user is still valid
        assertTrue(authService.authenticate("test_user", "newPassword123"));

        // verify the duplicate password doesn’t override the original
        assertFalse(authService.authenticate("test_user", "newPassword123"));
    }
}
