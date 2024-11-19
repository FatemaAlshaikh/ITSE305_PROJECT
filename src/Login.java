import java.util.HashMap;
import java.util.Map;
// Data Layer
// The User class represents a user with a username and password.
class User {
    private String username;
    private String password;

    // Constructor initializes user with a username and password.
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}

// UserRepository manages user data in a simple in-memory map.
// For storing and retrieving user information.
class UserRepository {
    private Map<String, User> users = new HashMap<>();

    // Adds a user to the repository
    public void addUser(User user) {
        users.put(user.getUsername(), user);
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
    private UserRepository userRepository;

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

        // Check if user exists and password matches
        return user != null && user.getPassword().equals(password);
    }
}

// Presentation Layer
// LoginController serves as the presentation layer for handling user interaction
// for login operations. It interacts with the AuthenticationService to manage
// user login attempts.
class LoginController {
    private AuthenticationService authService;

    // Constructor initializes the controller with an AuthenticationService
    public LoginController(AuthenticationService authService) {
        this.authService = authService;
    }

    // Handles a login attempt by checking the provided credentials with the AuthenticationService.
    // Outputs the result of the authentication to the console.
    public void login(String username, String password) {
        boolean isAuthenticated = authService.authenticate(username, password);

        // Display the result of the login attempt to the console
        if (isAuthenticated) {
            System.out.println("Login successful! Access granted.");
        } else {
            System.out.println("Login failed. Invalid username or password.");
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