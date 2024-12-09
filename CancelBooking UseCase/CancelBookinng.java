import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
// Data Layer
// Represents a reservation with details like customer name, room type, room number, price, CPR, phone number, and booking date.
class Reservation {
    private String customerName;
    private String roomType;
    private String roomNumber;
    private double price;
    private String cpr;
    private String phoneNumber;
    private LocalDate bookingDate;
    // Constructor to initialize reservation details
    public Reservation(String customerName, String roomType, String roomNumber, double price, String cpr, String phoneNumber, LocalDate bookingDate) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.price = price;
        this.cpr = cpr;
        this.phoneNumber = phoneNumber;
        this.bookingDate = bookingDate;
    }
    // Getter for customer Name
    public String getcustomerName(){
        return customerName;
    }
    // Getter for room Type
    public String getroomType(){
        return roomType;
    }
    // Getter for room number
    public String getRoomNumber() {
        return roomNumber;
    }
    // Getter for price
    public double getprice(){
        return price;
    }
    // Getter for CPR
    public String getCpr() {
        return cpr;
    }
    // Getter for Phone Number
    public String getphoneNumber() {
        return phoneNumber;
    }

    // Getter for booking date
    public LocalDate getBookingDate() {
        return bookingDate;
    }
    // String representation of a reservation
    @Override
    public String toString() {
        return "Customer Name: " + customerName + ", Room Type: " + roomType +
                ", Room Number: " + roomNumber + ", Price: $" + price +
                ", CPR: " + cpr + ", Phone Number: " + phoneNumber +
                ", Booking Date: " + bookingDate;
    }
}
// Manages a collection of reservations, allowing adding, removing, and checking existence of reservations.
class ReservationRepository {
    private List<Reservation> reservations;
    // Initializes the repository with an empty list of reservations
    public ReservationRepository() {
        this.reservations = new ArrayList<>();
    }
    // Adds a new reservation to the repository
    public void add(Reservation reservation) {
        reservations.add(reservation);
    }
    // Removes a reservation based on room number and CPR; returns true if removed
    public boolean remove(String roomNumber, String cpr) {
        return reservations.removeIf(reservation -> reservation.getRoomNumber().equals(roomNumber) && reservation.getCpr().equals(cpr));
    }
    // Returns a list of all reservations
    public List<Reservation> getAll() {
        return reservations;
    }
    // Checks if a reservation exists based on room number and CPR
    public boolean exists(String roomNumber, String cpr) {
        return reservations.stream().anyMatch(reservation -> reservation.getRoomNumber().equals(roomNumber) && reservation.getCpr().equals(cpr));
    }
}
// Business Layer
// Provides methods for reservation management, including adding, canceling, and displaying reservations.
class ReservationService {
    private ReservationRepository repository;
    // Initializes the service with a given reservation repository
    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }
    // Adds a reservation to the repository
    public void addReservation(String customerName, String roomType, String roomNumber, double price, String cpr, String phoneNumber, LocalDate bookingDate) {
        Reservation newReservation = new Reservation(customerName, roomType, roomNumber, price, cpr, phoneNumber, bookingDate);
        repository.add(newReservation);
        System.out.println("Reservation added successfully.");
    }
    // Cancels a reservation after confirmation
    public void cancelReservation(String roomNumber, String cpr) {
        if (repository.exists(roomNumber, cpr)) {
            System.out.print("Are you sure you want to cancel the reservation for room " + roomNumber + "? (yes or no): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("yes")) {
                Reservation reservation = repository.getAll().stream()
                        .filter(r -> r.getRoomNumber().equals(roomNumber) && r.getCpr().equals(cpr))
                        .findFirst().orElse(null);
                if (reservation != null) {
                    processRefund(reservation);
                    if (repository.remove(roomNumber, cpr)) {
                        System.out.println("Reservation for room " + roomNumber + " has been canceled successfully.");
                    }
                } else {
                    System.out.println("No reservation found for room " + roomNumber + " with CPR " + cpr + ".");
                }
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found for room " + roomNumber + " with CPR " + cpr + ".");
        }
    }
    // Processes the refund based on the cancellation policy
    private void processRefund(Reservation reservation) {
        LocalDate today = LocalDate.now();
        LocalDate bookingDate = reservation.getBookingDate();
        // Check if cancellation is within 24 hours of booking
        if (today.isBefore(bookingDate.minusDays(1))) {
            System.out.println("Refund processed: Full refund for reservation.");
        } else {
            System.out.println("Refund processed: One night's stay penalty applies.");
        }
    }
    // Returns a list of all reservations
    public List<Reservation> displayReservations() {
        return repository.getAll();
    }
    // Displays the cancellation policy
    public void showCancellationPolicy() {
        System.out.println("Cancellation Policy:");
        System.out.println("1. Cancellations made 24 hours before check-in are fully refundable.");
        System.out.println("2. Cancellations made within 24 hours of check-in will incur a penalty of one night's stay.");
    }
}
// Presentation Layer
// Main class to handle user interaction for making and canceling reservations
public class CancelBookinng {
    public static void main(String[] args) {
        ReservationRepository repository = new ReservationRepository();
        ReservationService service = new ReservationService(repository);
        Scanner scanner = new Scanner(System.in);
        try {
            // Adding sample reservations
            service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now().minusDays(2));
            service.addReservation("Fatema Mohamed", "Standard", "14", 100.0, "030056722", "00968-54379124", LocalDate.now().minusDays(1));
            service.addReservation("Zahra Husan", "Deluxe", "70", 150.0, "010400357", "00973-33367225", LocalDate.now());
            service.addReservation("Ahmed Amer", "Standard", "44", 100.0, "327799002", "00966-36598735", LocalDate.now().minusDays(3));
            service.addReservation("Noora Duaij", "Deluxe", "32", 150.0, "070050349", "00968-34567890", LocalDate.now());
            service.addReservation("Ali Hasan", "Standard", "68", 100.0, "760606911", "00966-0536417547", LocalDate.now().minusDays(2));
            // Displaying reservations
            displayReservations(service);
            // Show cancellation policy
            service.showCancellationPolicy();
            // Canceling a reservation
            System.out.print("\nEnter room number to cancel: ");
            String roomNumber = scanner.nextLine();
            System.out.print("Enter your CPR number: ");
            String cpr = scanner.nextLine();
            service.cancelReservation(roomNumber, cpr, scanner);
            // Displaying updated reservations
            displayReservations(service);
        } finally {
            scanner.close();
        }
    }
    // Displays the current reservations to the user
    static void displayReservations(ReservationService service) {
        List<Reservation> reservations = service.displayReservations();
        if (reservations.isEmpty()) {
            System.out.println("No current reservations.");
        } else {
            System.out.println("Current Reservations:");
            reservations.forEach(System.out::println);
        }
    }
}