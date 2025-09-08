import java.util.Random;

class BankAccount {
    // Fields
    private String accountHolder;
    private int accountNumber;
    private double balance;
    
    // Static fields
    private static int totalAccountsCreated = 0;
    private static final Random random = new Random();
    private static final int MIN_ACCOUNT_NUMBER = 100000;
    private static final int MAX_ACCOUNT_NUMBER = 999999;
    
    // 1. Default constructor → balance = 0
    public BankAccount() {
        this.accountHolder = "Unknown";
        this.accountNumber = generateRandomAccountNumber();
        this.balance = 0.0;
        totalAccountsCreated++;
    }
    
    // 2. Constructor with name → assigns random account number
    public BankAccount(String accountHolder) {
        this(); // Call default constructor first
        this.accountHolder = accountHolder;
    }
    
    // 3. Constructor with name and initial balance → assigns both
    public BankAccount(String accountHolder, double initialBalance) {
        this(accountHolder); // Call constructor with name
        this.balance = initialBalance;
    }
    
    // Method to generate random account number
    private int generateRandomAccountNumber() {
        return random.nextInt(MAX_ACCOUNT_NUMBER - MIN_ACCOUNT_NUMBER + 1) + MIN_ACCOUNT_NUMBER;
    }
    
    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Deposit amount must be positive!");
            return;
        }
        balance += amount;
        System.out.println("✅ Deposited: ₹" + amount + " | New Balance: ₹" + balance);
    }
    
    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Withdrawal amount must be positive!");
            return;
        }
        
        if (amount > balance) {
            System.out.println("❌ Insufficient funds! Available: ₹" + balance);
            return;
        }
        
        balance -= amount;
        System.out.println("✅ Withdrew: ₹" + amount + " | Remaining Balance: ₹" + balance);
    }
    
    // Display account details
    public void displayAccount() {
        System.out.println("🏦 Account Details:");
        System.out.println("─────────────────────────");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance:        ₹" + balance);
        System.out.println("─────────────────────────");
        System.out.println();
    }
    
    // Transfer method (bonus feature)
    public void transfer(BankAccount recipient, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Transfer amount must be positive!");
            return;
        }
        
        if (amount > balance) {
            System.out.println("❌ Insufficient funds for transfer! Available: ₹" + balance);
            return;
        }
        
        this.withdraw(amount);
        recipient.deposit(amount);
        System.out.println("✅ Transferred ₹" + amount + " to " + recipient.getAccountHolder());
    }
    
    // Getters
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public static int getTotalAccountsCreated() {
        return totalAccountsCreated;
    }
    
    // Setters
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    
    // Static method to display bank info
    public static void displayBankInfo() {
        System.out.println("🏛️  Bank Information:");
        System.out.println("Total Accounts Created: " + totalAccountsCreated);
        System.out.println("Account Number Range: " + MIN_ACCOUNT_NUMBER + " - " + MAX_ACCOUNT_NUMBER);
        System.out.println();
    }
}

// Main class to test the Bank Account System
public class BankingSystem {
    public static void main(String[] args) {
        System.out.println("💳 Welcome to Java Banking System! 💳\n");
        
        // Display bank information
        BankAccount.displayBankInfo();
        
        // Create accounts using different constructors
        BankAccount[] accounts = {
            // 1. Default constructor
            new BankAccount(),
            
            // 2. Constructor with name only
            new BankAccount("Alice Johnson"),
            
            // 3. Constructor with name and initial balance
            new BankAccount("Bob Smith", 5000.0),
            
            // Additional accounts
            new BankAccount("Charlie Brown", 10000.0),
            new BankAccount("Diana Prince", 2500.0)
        };
        
        // Display all accounts initially
        System.out.println("📋 Initial Account Details:");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account #" + (i + 1) + ":");
            accounts[i].displayAccount();
        }
        
        // Perform transactions
        System.out.println("💸 Performing Transactions:");
        System.out.println("─────────────────────────");
        
        // Deposit operations
        accounts[0].deposit(1000.0);
        accounts[1].deposit(2000.0);
        accounts[2].deposit(1500.0);
        System.out.println();
        
        // Withdrawal operations
        accounts[2].withdraw(1000.0);
        accounts[3].withdraw(3000.0); // This should fail (insufficient funds)
        accounts[4].withdraw(500.0);
        System.out.println();
        
        // Transfer between accounts
        System.out.println("🔄 Transfer Operations:");
        accounts[2].transfer(accounts[0], 2000.0); // Bob transfers to default account
        accounts[4].transfer(accounts[1], 1000.0); // Diana transfers to Alice
        System.out.println();
        
        // Attempt invalid operations
        System.out.println("⚠️  Testing Invalid Operations:");
        accounts[0].deposit(-100.0); // Negative deposit
        accounts[1].withdraw(-50.0); // Negative withdrawal
        accounts[2].withdraw(100000.0); // Excessive withdrawal
        System.out.println();
        
        // Update account holder name for default account
        accounts[0].setAccountHolder("John Doe");
        
        // Display final account details
        System.out.println("📊 Final Account Details:");
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Account #" + (i + 1) + ":");
            accounts[i].displayAccount();
        }
        
        // Display transaction summary
        displayTransactionSummary(accounts);
    }
    
    // Helper method to display transaction summary
    private static void displayTransactionSummary(BankAccount[] accounts) {
        System.out.println("💰 Transaction Summary:");
        System.out.println("─────────────────────────");
        
        double totalBankBalance = 0;
        double highestBalance = 0;
        String richestCustomer = "";
        
        for (BankAccount account : accounts) {
            totalBankBalance += account.getBalance();
            
            if (account.getBalance() > highestBalance) {
                highestBalance = account.getBalance();
                richestCustomer = account.getAccountHolder();
            }
        }
        
        System.out.println("Total Bank Balance: ₹" + totalBankBalance);
        System.out.println("Highest Balance: ₹" + highestBalance + " (" + richestCustomer + ")");
        System.out.println("Total Accounts: " + BankAccount.getTotalAccountsCreated());
        System.out.println("Average Balance: ₹" + (totalBankBalance / accounts.length));
        System.out.println("─────────────────────────");
        System.out.println();
    }
}