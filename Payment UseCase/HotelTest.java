import static org.junit.Assert.*;
import org.junit.Test;


public class HotelTest {

    @Test
    public void testProcessPaymentCreditCard() {
        Payment.PaymentService paymentService = new Payment.PaymentService();
        boolean result = paymentService.processPayment("Credit Card", 150.0);
        assertTrue(result);
    }

    @Test
    public void testProcessPaymentDebitCard() {
        Payment.PaymentService paymentService = new Payment.PaymentService();
        boolean result = paymentService.processPayment("Debit Card", 200.0);
        assertTrue(result);
    }

    @Test
    public void testProcessPaymentBenefitPay() {
        Payment.PaymentService paymentService = new Payment.PaymentService();
        boolean result = paymentService.processPayment("Benefit Pay", 350.0);
        assertTrue(result);
    }

    @Test
    public void testKeepPayCreditCard() {
        Payment.PaymentService.PaymentStore paymentStore = new Payment.PaymentService().new PaymentStore();
        boolean result = paymentStore.keepPay("Credit Card", 150.0);
        assertTrue(result);
    }

    @Test
    public void testKeepPayDebitCard() {
        Payment.PaymentService.PaymentStore paymentStore = new Payment.PaymentService().new PaymentStore();
        boolean result = paymentStore.keepPay("Debit Card", 200.0);
        assertTrue(result);
    }

    @Test
    public void testKeepPayBenefitPay() {
        Payment.PaymentService.PaymentStore paymentStore = new Payment.PaymentService().new PaymentStore();
        boolean result = paymentStore.keepPay("Benefit Pay", 350.0);
        assertTrue(result);
    }
}