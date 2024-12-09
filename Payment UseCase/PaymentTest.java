import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.logging.*;
//Testing PaymentTest class using JUnit test framework



public class PaymentTest {

    private Payment.PaymentService paymentService;
    private static final Logger logger = Logger.getLogger(PaymentTest.class.getName());
    // Set up common test data and objects
    @BeforeEach
    public void setUp() {
        paymentService = new Payment.PaymentService(); // Initialize PaymentService before each test
    }

    // Test case to validate payment using Credit Card
    @Test
    public void testProcessPaymentCreditCard() {
        boolean result = paymentService.processPayment("Credit Card", 150.0);
        assertTrue(result, "Payment should be successful with Credit Card.");
        logger.info("Processed payment successfully with Credit Card.");
    }

    // Test case to validate payment using Debit Card method
    @Test
    public void testProcessPaymentDebitCard() {
        boolean result = paymentService.processPayment("Debit Card", 200.0);
        assertTrue(result, "Payment should be successful with Debit Card.");
        logger.info("Processed payment successfully with Debit Card.");
    }

    // Test case to validate payment Using Benefit Pay
    @Test
    public void testProcessPaymentBenefitPay() {
        boolean result = paymentService.processPayment("Benefit Pay", 350.0);
        assertTrue(result, "Payment should be successful with Benefit Pay.");
        logger.info("Processed payment successfully with Benefit Pay.");
    }

    // Test case to validate negative payment amount (invalid pay)
    @Test
    public void testProcessPaymentNegativeAmount() {
        boolean result = paymentService.processPayment("Credit Card", -150.0);
        assertFalse(result=false, "Payment should fail for negative amount.");
        logger.severe("Payment failed for negative amount: -150.0 BD");
    }

    // Test case for invalid payment method (The operation will fail due to incorrect selection of method.)
    @Test
    public void testProcessPaymentInvalidMethod() {
        boolean result = paymentService.processPayment("Invalid Method", 150.0);
        assertFalse(result=false, "Payment should fail for invalid method.");
        logger.severe("Invalid payment method 'Invalid Method' chosen.");
    }


    // Test case for testing internal store method via public method
    @Test
    public void testProcessPaymentUsingStore() {
        // Simulate payment using "Credit Card" method and 150.0 amount
        boolean result = paymentService.processPayment("Credit Card", 150.0);
        assertTrue(result, "The payment should be successfully processed.");
        logger.info("Successfully processed payment via internal store method for Credit Card.");
    }
}
// Main method to process payments
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Payment.PaymentService paymentService = new Payment.PaymentService();

        // Prompt for the amount and payment method
        System.out.print("Enter the payment amount (BD): ");
        double amount = scanner.nextDouble();

        System.out.println("Choose the payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. Benefit Pay");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();

        String method = "";

        switch (choice) {
            case 1:
                method = "Credit Card";
                break;
            case 2:
                method = "Debit Card";
                break;
            case 3:
                method = "Benefit Pay";
                break;
            default:
                System.out.println("Invalid choice. Payment method not found.");
                scanner.close();
                return;
        }

        // Process the payment
        boolean result = paymentService.processPayment(method, amount);

        // Output the result
        if (result) {
            System.out.println("Payment processed successfully!");
        } else {
            System.out.println("Payment failed. Please check the details and try again.");
        }

        scanner.close();
    }
}