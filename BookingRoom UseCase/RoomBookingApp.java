import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Presentation Layer: This class handles user interaction 
//(room number and customer name) displaying booking results.
public class RoomBookingApp {
    private BookingService bookingService;
    private Scanner scanner;


    // Constructor initializes the booking service and scanner
    //
    public RoomBookingApp() {
        this.bookingService = new BookingService(new RoomRepository());
        this.scanner = new Scanner(System.in);
    }

    // Method to start the booking process
    public void start() {
        System.out.println("Welcome to the Room Booking System!");
        System.out.print("Enter the room number to book: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        boolean booked = bookingService.bookRoom(roomNumber, customerName);
        if (booked) {
            System.out.println("Room " + roomNumber + " booked successfully for " + customerName + "!");
        } else {
            System.out.println("Room " + roomNumber + " is already booked or does not exist.");
        }
    }

    public static void main(String[] args) {
        RoomBookingApp app = new RoomBookingApp();
        app.start();
    }
}

// Business Layer: This class contains the logic for booking rooms
//(BookingService):It checks if a room is available and updates its status accordingly.
class BookingService {
    private RoomRepository roomRepository;

    // Constructor
    public BookingService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Book a room
    public boolean bookRoom(String roomNumber, String customerName) {
        Room room = roomRepository.getRoom(roomNumber);
        if (room != null && !room.isBooked()) {
            room.setBooked(true);
            room.setCustomerName(customerName);
            return true; // Successful booking
        }
        return false; // Booking failed
    }
}

// Data Layer: Represents the data storage for rooms
class Room {
    private String roomNumber;
    private boolean booked;

    // Constructor to initialize room details
    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
        this.booked = false;
    }

    // Getters and setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setCustomerName(String customerName) {
    }
}

// Class representing the repository of rooms
class RoomRepository {
    private Map<String, Room> rooms;

    // Constructor initializes a set of rooms
    public RoomRepository() {
        rooms = new HashMap<>();
        initializeRooms();
    }

    // Method to initialize some rooms for the example
    private void initializeRooms() {
        rooms.put("101", new Room("101"));
        rooms.put("102", new Room("102"));
        rooms.put("103", new Room("103"));
    }

    // Method to get a room by its number
    public Room getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }
}
