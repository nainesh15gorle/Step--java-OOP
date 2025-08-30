
public class BankAccount {
    // Instance variables (unique to each object)
    private final String accountNumber;
    private double balance;
    
    // Static variables (shared across all objects)
    private static int totalAccounts = 0;
    private static double totalBankBalance = 0;
    public static final String BANK_NAME = "JavaBank";
    public static final double INTEREST_RATE = 0.035; // 3.5%
    
    // Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        totalAccounts++;
        totalBankBalance += initialBalance;
    }
    
    // Instance methods (operate on specific object)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            totalBankBalance += amount;
            System.out.printf("Deposited $%.2f to account %s%n", amount, accountNumber);
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            totalBankBalance -= amount;
            System.out.printf("Withdrew $%.2f from account %s%n", amount, accountNumber);
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    // Static methods (operate on class level, not object specific)
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    public static double getTotalBankBalance() {
        return totalBankBalance;
    }
    
    public static double calculateInterest(double principal) {
        return principal * INTEREST_RATE;
    }
    
    public static void displayBankInfo() {
        System.out.printf("%s Bank Statistics:%n", BANK_NAME);
        System.out.printf("Total Accounts: %d%n", totalAccounts);
        System.out.printf("Total Bank Balance: $%.2f%n", totalBankBalance);
        System.out.printf("Current Interest Rate: %.1f%%%n", INTEREST_RATE * 100);
    }
    
    // Instance method using both instance and static members
    public void displayAccountInfo() {
        System.out.printf("Account: %s%n", accountNumber);
        System.out.printf("Balance: $%.2f%n", balance);
        System.out.printf("Estimated yearly interest: $%.2f%n", calculateInterest(balance));
        System.out.println("---");
    }
}

// Demo class to test the differences
public class InstanceStaticDemo {
    public static void main(String[] args) {
        System.out.println("=== BANK ACCOUNT DEMO ===");
        System.out.println("Static members accessed directly via class:");
        System.out.println("Bank Name: " + BankAccount.BANK_NAME);
        System.out.println("Interest Rate: " + (BankAccount.INTEREST_RATE * 100) + "%");
        
        // Display initial bank info using static method
        BankAccount.displayBankInfo();
        System.out.println();
        
        // Create account objects
        System.out.println("Creating accounts...");
        BankAccount account1 = new BankAccount("ACC001", 1000.00);
        BankAccount account2 = new BankAccount("ACC002", 2500.00);
        BankAccount account3 = new BankAccount("ACC003", 500.00);
        
        // Display updated bank info
        System.out.println("\nAfter account creation:");
        BankAccount.displayBankInfo();
        System.out.println();
        
        // Demonstrate instance methods
        System.out.println("=== ACCOUNT OPERATIONS ===");
        account1.deposit(200.00);
        account2.withdraw(300.00);
        account3.deposit(150.00);
        
        System.out.println("\nUpdated account balances:");
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        account3.displayAccountInfo();
        
        // Show final bank statistics
        System.out.println("=== FINAL BANK STATISTICS ===");
        System.out.printf("Total Accounts (static access): %d%n", BankAccount.getTotalAccounts());
        System.out.printf("Total Bank Balance (static access): $%.2f%n", BankAccount.getTotalBankBalance());
        
        // Demonstrate static method usage without objects
        System.out.printf("\nInterest on $1000: $%.2f%n", BankAccount.calculateInterest(1000));
        
        // Key differences demonstration
        System.out.println("\n=== KEY DIFFERENCES ===");
        System.out.println("Instance members: accountNumber, balance, deposit(), withdraw()");
        System.out.println("Static members: totalAccounts, totalBankBalance, BANK_NAME, getTotalAccounts()");
        System.out.println("Instance → Per object | Static → Shared across all objects");
    }
}