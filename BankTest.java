public abstract class BankAccount {
    protected double balance;
    public BankAccount(double balance) {
        this.balance = balance;
    }
    public abstract void calculateInterest();
    public void displayBalance() {
        System.out.println("Balance: " + balance);
    }
}
public class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super(balance);
    }
    @Override
    public void calculateInterest() {
        double interest = balance * 0.04;
        System.out.println("Savings Interest: " + interest);
    }
}
public class SavingsAccount extends BankAccount {
    public SavingsAccount(double balance) {
        super(balance);
    }
    @Override
    public void calculateInterest() {
        double interest = balance * 0.04;
        System.out.println("Savings Interest: " + interest);
    }
}
public class CurrentAccount extends BankAccount {
    public CurrentAccount(double balance) {
        super(balance);
    }
    @Override
    public void calculateInterest() {
        double interest = balance * 0.02;
        System.out.println("Current Interest: " + interest);
    }
}
public class CurrentAccount extends BankAccount {
    public CurrentAccount(double balance) {
        super(balance);
    }
    @Override
    public void calculateInterest() {
        double interest = balance * 0.02;
        System.out.println("Current Interest: " + interest);
    }
}
public class BankTest {
    public static void main(String[] args) {
        BankAccount s = new SavingsAccount(10000);
        s.displayBalance();
        s.calculateInterest();
        BankAccount c = new CurrentAccount(12000);
        c.displayBalance();
        c.calculateInterest();
    }
}
