import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Presentation Layer: This class handles user interaction (room number and customer name) and displays booking results.
public class RoomBookingApp {

    private BookingService bookingService;
    private Scanner scanner;

    // Constructor initializes the booking service and scanner
    public RoomBookingApp() {
        this.bookingService = new BookingService(new RoomRepository());
        this.scanner = new Scanner(System.in);
    }

    // Method to start the booking process
    public void start() {
        while (true) {
            System.out.println("\nWelcome to the Room Booking System!");
            System.out.println("1. Book a room");
            System.out.println("2. Cancel a booking");
            System.out.println("3. List all rooms");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> bookRoom();
                case 2 -> cancelBooking();
                case 3 -> listRooms();
                case 4 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to book a room
    private void bookRoom() {
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

    // Method to cancel a booking
    private void cancelBooking() {
        System.out.print("Enter the room number to cancel booking: ");
        String roomNumber = scanner.nextLine();

        boolean canceled = bookingService.cancelBooking(roomNumber);
        if (canceled) {
            System.out.println("Booking for room " + roomNumber + " has been canceled.");
        } else {
            System.out.println("Room " + roomNumber + " is not booked or does not exist.");
        }
    }


    // Method to list all rooms
    private void listRooms() {
        System.out.println("\nRoom List:");
        bookingService.listAllRooms();
    }

    public static void main(String[] args) {
        RoomBookingApp app = new RoomBookingApp();
        app.start();
    }
}

// Business Layer: This class contains the logic for booking rooms
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
            return true; // Successful booking
        }
        return false; // Booking failed
    }

    // Cancel a room booking
    public boolean cancelBooking(String roomNumber) {
        Room room = roomRepository.getRoom(roomNumber);
        if (room != null && room.isBooked()) {
            room.setBooked(false);
            return true; // Successfully canceled booking
        }
        return false; // Cancelation failed
    }

    // List all rooms and their availability
    public void listAllRooms() {
        Map<String, Room> rooms = roomRepository.getAllRooms();
        rooms.forEach((number, room) -> {
            String status = room.isBooked() ? "Booked" : "Available";
            System.out.println("Room " + number + ": " + status);
        });
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

    // Method to get all rooms
    public Map<String, Room> getAllRooms() {
        return rooms;
    }
}
