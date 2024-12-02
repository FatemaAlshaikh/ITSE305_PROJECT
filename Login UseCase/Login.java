import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.concurrent.ConcurrentHashMap;

// Data Layer
// The User class represents a user with a username and password.


class User {
    private final String username;
    private String password;

    // a static BCryptPasswordEncoder for hashing and verifying passwords
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Constructor initializes user with a username and password and hashes the provided password.
    public User(String username, String password) {
        this.username = username;
        this.password = hashPassword(password);
    }
    // Getter for username
    public String getUsername() {
        return username;
    }
    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }
    // Getter for password
    public String getPassword() {
        return password;
    }
    // Setter for password (re-hashes the new password)
    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    // verifies if a plain-text password matches the stored hashed password
    public boolean verifyPassword(String plainPassword){
        return passwordEncoder.matches(plainPassword, this.password);
    }

    // private method to hash a password using BCrypt
    private String hashPassword(String plainPassword){
        return passwordEncoder.encode(plainPassword);
    }

}

// UserRepository manages user data in a simple in-memory map.
// For storing and retrieving user information.


class UserRepository {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    // Adds a user to the repository
    public boolean addUser(User user) {
        if (users.containsKey(user.getUsername())){
            return false; // prevent duplicate users
        }
        users.put(user.getUsername(), user);
        return true;
    }

    // Retrieves a user by their username; returns null if not found.
    public User findByUsername(String username) {
        return users.get(username);
    }
}

// Business Layer
// AuthenticationService represents the business layer for user login operations.
// for user authentication.


class AuthenticationService {
    private final UserRepository userRepository;

    // Constructor initializes with a UserRepository
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Verifies the user's credentials by checking if the username and password are valid.
    // Returns true if the user is authenticated; otherwise, returns false.
    public boolean authenticate(String username, String password) {

        // Handle null inputs to prevent NullPointerException
        if (username == null || password == null) {
            return false; // Treat null inputs as invalid credentials
        }
        User user = userRepository.findByUsername(username);

        // use verifyPassword for secure password checking
        return user != null && user.verifyPassword(password);
    }
}

// Presentation Layer
// LoginController serves as the presentation layer for handling user interaction
// for login operations. It interacts with the AuthenticationService to manage
// user login attempts.


class LoginController {
    private final AuthenticationService authService;

    // Constructor initializes the controller with an AuthenticationService
    public LoginController(AuthenticationService authService) {
        this.authService = authService;
    }

    // Handles a login attempt by checking the provided credentials with the AuthenticationService.
    // Outputs the result of the authentication to the console.
    public String login(String username, String password) {
        boolean isAuthenticated = authService.authenticate(username, password);

        // Display the result of the login attempt to the console
        if (isAuthenticated) {
            return "Login successful! Access granted.";
        } else {
            return "Login failed. Invalid username or password.";
        }
    }
}

public class Login {
    public static void main(String[] args) {
        // Initialize data, business, and presentation layers
        UserRepository userRepository = new UserRepository();
        AuthenticationService authService = new AuthenticationService(userRepository);
        LoginController loginController = new LoginController(authService);

        // Adding sample users for testing
        userRepository.addUser(new User("john_doe", "password123"));
        userRepository.addUser(new User("jane_smith", "securePass!"));

        // Simulate login attempts with different cases
        System.out.println("Login Attempt 1:");
        loginController.login("john_doe", "password123"); // Case 1: Successful login

        System.out.println("Login Attempt 2:");
        loginController.login("jane_smith", "wrongPassword"); // Case 2: Failed login (incorrect password)

        System.out.println("Login Attempt 3:");
        loginController.login("unknown_user", "anyPassword"); // Case 3: Failed login (user not found)
    }
}
