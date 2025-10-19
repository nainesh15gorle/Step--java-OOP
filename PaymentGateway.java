// ...existing code...
class Payment {
    public void pay() {
        System.out.println("Generic payment");
    }
}

class CreditCardPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Credit Card payment");
    }
}

class WalletPayment extends Payment {
    @Override
    public void pay() {
        System.out.println("Wallet payment");
    }
}

public class PaymentGateway {
    public static void main(String[] args) {
        Payment[] payments = { new CreditCardPayment(), new WalletPayment() };
        for (Payment p : payments) {
            System.out.println("Class: " + p.getClass().getSimpleName());
            p.pay();
        }
    }
}
// ...existing code...