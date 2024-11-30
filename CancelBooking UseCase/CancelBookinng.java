import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
// Data Layer
// Represents a reservation with details like customer name, room type, room number, price, CPR, phone number, and booking date.
class Reservation {
    private final String customerName;
    private final String roomType;
    private final String roomNumber;
    private final double price;
    private final String cpr;
    private final String phoneNumber;
    private final LocalDate bookingDate;
    // Constructor to initialize reservation details
    public Reservation(String customerName, String roomType, String roomNumber, double price, String cpr, String phoneNumber, LocalDate bookingDate) {
        // Validate the inputs
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");}
        if (roomType == null || roomType.isEmpty()) {
            throw new IllegalArgumentException("Room type cannot be empty."); }
        if (roomNumber == null || roomNumber.isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be empty.");}
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero."); }
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number."); }
        if (!isValidCpr(cpr)) {
            throw new IllegalArgumentException("Invalid CPR."); }
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
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    private boolean isValidCpr(String cpr) {
        return cpr != null && cpr.matches("\\d{10}");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return roomNumber.equals(that.roomNumber) && cpr.equals(that.cpr);
    }
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, cpr);
    }
}
// Manages a collection of reservations, allowing adding, removing, and checking existence of reservations.
class ReservationRepository {
    private final  Map<String,Reservation> reservationsMap;
    // Initializes the repository with an empty list of reservations
    public ReservationRepository() {
        this.reservationsMap = new HashMap<>();
    }
    // Adds a new reservation to the repository
    public synchronized void add(Reservation reservation) {
        String key = generateKey(reservation.getRoomNumber(), reservation.getCpr());
        reservationsMap.put(key, reservation);
    }
    // Removes a reservation based on room number and CPR; returns true if removed
    public synchronized boolean remove(String roomNumber, String cpr) {
        String key = generateKey(roomNumber, cpr);
        return reservationsMap.remove(key) != null;
    }
    // Returns a list of all reservations
    public List<Reservation> getAll() {
        return new ArrayList<>(reservationsMap.values());
    }
    // Checks if a reservation exists based on room number and CPR
    public boolean exists(String roomNumber, String cpr) {
        String key = generateKey(roomNumber, cpr);
        return reservationsMap.containsKey(key);
    }
    private String generateKey(String roomNumber, String cpr) {
        return roomNumber + ":" + cpr;
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
    public String cancelReservation(String roomNumber, String cpr) {
        if (repository.exists(roomNumber, cpr)) {
            Reservation reservation = repository.getAll().stream()
                    .filter(r -> r.getRoomNumber().equals(roomNumber) && r.getCpr().equals(cpr))
                    .findFirst().orElse(null);

            if (reservation != null) {
                return processRefund(reservation);
            } else {
                return "No reservation found for room " + roomNumber + " with CPR " + cpr + ".";
            }
        } else {
            return "No reservation found for room " + roomNumber + " with CPR " + cpr + ".";
        }
    }
    // Processes the refund based on the cancellation policy
    private String processRefund(Reservation reservation) {
        LocalDate today = LocalDate.now();
        LocalDate bookingDate = reservation.getBookingDate();
        boolean isNoShow = today.isAfter(bookingDate);
        // Check if cancellation is within 24 hours of booking
        if (isNoShow) {
            return "Refund processed: No-show penalty applies. No refund available.";
        } else if (today.isBefore(bookingDate.minusDays(1))) {
            return "Refund processed: Full refund for reservation.";
        } else {
            return "Refund processed: One night's stay penalty applies.";
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
        System.out.println("3. No-shows will not receive any refund.");
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
                // Adding sreservations
                displayReservations(service);
 
                // Displaying current reservations
                displayReservations(service);
     
                // Show cancellation policy
                service.showCancellationPolicy();
     
                // Canceling a reservation
                String roomNumber = getUserInput("Enter room number to cancel: ", scanner);
                String cpr = getUserInput("Enter your CPR number: ", scanner);
     
                if (validateRoomNumber(roomNumber) && validateCpr(cpr)) {
                    String cancellationResult = service.cancelReservation(roomNumber, cpr);
                    System.out.println(cancellationResult);
                } else {
                    System.out.println("Invalid input. Please provide valid room number and CPR.");
                }
     
                // Displaying updated reservations
                displayReservations(service);
            } finally {
                scanner.close();
            }
        }
            // Adding e reservations
            @SuppressWarnings("unused")
            private static void addReservation(ReservationService service) {
                service.addReservation("Fatema Alshaikh", "Deluxe", "1", 150.0, "020900581", "00973-36784589", LocalDate.now().minusDays(2));
                service.addReservation("Fatema Mohamed", "Standard", "14", 100.0, "030056722", "00968-54379124", LocalDate.now().minusDays(1));
                service.addReservation("Zahra Husan", "Deluxe", "70", 150.0, "010400357", "00973-33367225", LocalDate.now());
                service.addReservation("Ahmed Amer", "Standard", "44", 100.0, "327799002", "00966-36598735", LocalDate.now().minusDays(3));
                service.addReservation("Noora Duaij", "Deluxe", "32", 150.0, "070050349", "00968-34567890", LocalDate.now());
                service.addReservation("Ali Hasan", "Standard", "68", 100.0, "760606911", "00966-0536417547", LocalDate.now().minusDays(2)); }
    // Displays the current reservations to the user
   public static void displayReservations(ReservationService service) {
        List<Reservation> reservations = service.displayReservations();
        if (reservations.isEmpty()) {
            System.out.println("No current reservations.");
        } else {
            System.out.println("Current Reservations:");
            reservations.forEach(System.out::println);
        } }
 // Method to get user input
 private static String getUserInput(String prompt, Scanner scanner) {
    System.out.print(prompt);
    return scanner.nextLine().trim();}
// Validate the room number input (non-empty and numeric)
private static boolean validateRoomNumber(String roomNumber) {
    if (roomNumber.isEmpty()) {
        return false;}
    try {
        Integer.parseInt(roomNumber); // Validate if it's a number
        return true;
    } catch (NumberFormatException e) {
        return false;
    }}
// Validate the CPR input (non-empty and numeric)
private static boolean validateCpr(String cpr) {
    if (cpr.isEmpty()) {
        return false;}
    try {
        Long.parseLong(cpr); // Validate if it's a number
        return true;
    } catch (NumberFormatException e) {
        return false;
    }}
@Override
public String toString() {
    return "CancelBookinng []";
}}
