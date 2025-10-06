public interface PaymentGateway {
    void pay(double amount);
    void refund(double amount);
}
public class CreditCardPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via Credit Card: " + amount);
    }
    @Override
    public void refund(double amount) {
        System.out.println("Refund to Credit Card: " + amount);
    }
}
public class UPIPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid via UPI: " + amount);
    }
    @Override
    public void refund(double amount) {
        System.out.println("Refund to UPI: " + amount);
    }
}
public class PaymentTest {
    public static void main(String[] args) {
        PaymentGateway cc = new CreditCardPayment();
        cc.pay(1000);
        cc.refund(200);
        PaymentGateway upi = new UPIPayment();
        upi.pay(500);
        upi.refund(100);
    }
}
