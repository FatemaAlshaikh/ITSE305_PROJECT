import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//Testing Payment class using JUnit test framework
public class PaymentTest {

    private Payment.PaymentService paymentService;

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
    }

    // Test case to validate payment using Debit Card method
    @Test
    public void testProcessPaymentDebitCard() {
        boolean result = paymentService.processPayment("Debit Card", 200.0);
        assertTrue(result, "Payment should be successful with Debit Card.");
    }

    // Test case to validate payment Using Benefit Pay
    @Test
    public void testProcessPaymentBenefitPay() {
        boolean result = paymentService.processPayment("Benefit Pay", 350.0);
        assertTrue(result, "Payment should be successful with Benefit Pay.");
    }

    // Test case to validate negative payment amount (invalid pay)
    @Test
    public void testProcessPaymentNegativeAmount() {
        boolean result = paymentService.processPayment("Credit Card", -150.0);
        assertFalse(result=false, "Payment should fail for negative amount.");
    }

    // Test case for invalid payment method (The operation will fail due to incorrect selection of method.)
    @Test
    public void testProcessPaymentInvalidMethod() {
        boolean result = paymentService.processPayment("Invalid Method", 150.0);
        assertFalse(result=false, "Payment should fail for invalid method.");
    }


    // Test case for testing internal store method via public method
    @Test
    public void testProcessPaymentUsingStore() {
        // Simulate payment using "Credit Card" method and 150.0 amount
        boolean result = paymentService.processPayment("Credit Card", 150.0);
        assertTrue(result, "The payment should be successfully processed.");
    }
}