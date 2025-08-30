class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;
    private static int accountCounter = 0;
    
    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited $%.2f to account %s. New balance: $%.2f%n", 
                            amount, accountNumber, balance);
        } else {
            System.out.println("Deposit amount must be positive");
        }
    }
    
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
        } else if (amount > balance) {
            System.out.printf("Insufficient funds. Available: $%.2f%n", balance);
        } else {
            balance -= amount;
            System.out.printf("Withdrew $%.2f from account %s. New balance: $%.2f%n", 
                            amount, accountNumber, balance);
        }
    }
    
    public void checkBalance() {
        System.out.printf("Account %s balance: $%.2f%n", accountNumber, balance);
    }
    
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    private static String generateAccountNumber() {
        accountCounter++;
        return String.format("ACC%03d", accountCounter);
    }
    
    public void displayAccountInfo() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║        ACCOUNT INFORMATION   ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf ("║ Account: %-18s ║%n", accountNumber);
        System.out.printf ("║ Holder: %-19s ║%n", accountHolderName);
        System.out.printf ("║ Balance: $%-16.2f ║%n", balance);
        System.out.println("╚══════════════════════════════╝");
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== BANK ACCOUNT MANAGEMENT SYSTEM ===\n");
        
        // Create array of BankAccount objects
        BankAccount[] accounts = new BankAccount[3];
        
        // Create accounts
        accounts[0] = new BankAccount("Alice Johnson", 1000.00);
        accounts[1] = new BankAccount("Bob Smith", 2500.00);
        accounts[2] = new BankAccount("Carol Davis", 500.00);
        
        System.out.println("Total accounts created: " + BankAccount.getTotalAccounts());
        System.out.println();
        
        // Perform transactions
        System.out.println("=== TRANSACTIONS ===");
        accounts[0].deposit(200.00);
        accounts[1].withdraw(300.00);
        accounts[2].withdraw(600.00); // Should fail
        accounts[2].deposit(150.00);
        
        System.out.println();
        
        // Display all account information
        System.out.println("=== ACCOUNT DETAILS ===");
        for (BankAccount account : accounts) {
            account.displayAccountInfo();
        }
        
        // Demonstrate static vs instance variables
        System.out.println("=== STATIC vs INSTANCE VARIABLES ===");
        System.out.println("Static totalAccounts: " + BankAccount.getTotalAccounts() + " (shared across all objects)");
        System.out.print("Instance balance for account 1: ");
        accounts[0].checkBalance(); // Just call the method, don't print its return value
    }
}