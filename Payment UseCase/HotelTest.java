import static org.junit.Assert.*;
import org.junit.Test;




//Testing HotelTest class using JUnit test framework
public class HotelTest {
    // Helper method to test payment processing
    private void assertPaymentProcessing(String method, double amount) {
        Payment.PaymentService paymentService = new Payment.PaymentService();
        boolean result = paymentService.processPayment(method, amount);
        assertTrue("Payment should be processed successfully for method: " + method, result);
    }

    // Helper method to test payment storage
    private void assertPaymentStorage(String method, double amount) {
        Payment.PaymentService.PaymentStore paymentStore = new Payment.PaymentService().new PaymentStore();
        boolean result = paymentStore.keepPay(method, amount);
        assertTrue("Payment should be stored successfully for method: " + method, result);
    }
//checks if the payment method is correctly "Credit Card" and valid amounts =150
    @Test
    public void testProcessPaymentCreditCard() {
        Payment.PaymentService paymentService = new Payment.PaymentService();
        boolean result = paymentService.processPayment("Credit Card", 150.0);
        assertTrue(result);
    }
 //checks if the payment method is correctly "debit Card" and valid amounts =200
    @Test
    public void testProcessPaymentDebitCard() {
        Payment.PaymentService paymentService = new Payment.PaymentService();
        boolean result = paymentService.processPayment("Debit Card", 200.0);
        assertTrue(result);
    }
//checks if the payment method is correctly "Benefit Pay" and valid amounts =350
    @Test
    public void testProcessPaymentBenefitPay() {
        Payment.PaymentService paymentService = new Payment.PaymentService();
        boolean result = paymentService.processPayment("Benefit Pay", 350.0);
        assertTrue(result);
    }
//to ensuring that the method correctly processes payments when called directly.
    @Test
    public void testKeepPayCreditCard() {
        Payment.PaymentService.PaymentStore paymentStore = new Payment.PaymentService().new PaymentStore();
        boolean result = paymentStore.keepPay("Credit Card", 150.0);
        assertTrue(result);
    }
//this test checks that the keepPay() method works for the "Debit Card" payment option
    @Test
    public void testKeepPayDebitCard() {
        Payment.PaymentService.PaymentStore paymentStore = new Payment.PaymentService().new PaymentStore();
        boolean result = paymentStore.keepPay("Debit Card", 200.0);
        assertTrue(result);
    }
  //This test ensures that the keepPay() method correctly handles payments with the "Benefit Pay" method
    @Test
    public void testKeepPayBenefitPay() {
        Payment.PaymentService.PaymentStore paymentStore = new Payment.PaymentService().new PaymentStore();
        boolean result = paymentStore.keepPay("Benefit Pay", 350.0);
        assertTrue(result);
    }
}