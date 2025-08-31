import java.util.*;

class PersonalAccount {
    // Instance variables (unique per account)
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    // Static variables (shared by all accounts)
    private static int totalAccounts = 0;
    private static String bankName;

    // Constructor
    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;  // Increment when new account created
    }

    // Instance methods
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(accountHolderName + " received income: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid income amount!");
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(accountHolderName + " spent: " + amount + " (" + description + ")");
        } else {
            System.out.println("Invalid expense or insufficient balance!");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary ---");
        System.out.println("Bank: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Savings: " + calculateSavings());
        System.out.println("-----------------------");
    }

    // Static methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        return "ACCT" + (1000 + totalAccounts + 1);
    }
}

public class FinanceManagementSystem {
    public static void main(String[] args) {
        // Set common bank name (static variable)
        PersonalAccount.setBankName("GenZ Bank");

        // Create 3 accounts (instance variables are unique for each)
        PersonalAccount acc1 = new PersonalAccount("Alice");
        PersonalAccount acc2 = new PersonalAccount("Bob");
        PersonalAccount acc3 = new PersonalAccount("Charlie");

        // Perform transactions
        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1200, "Rent");
        acc1.addExpense(800, "Groceries");

        acc2.addIncome(7000, "Freelancing");
        acc2.addExpense(2500, "Laptop Purchase");

        acc3.addIncome(6000, "Part-time Job");
        acc3.addExpense(1000, "Travel");
        acc3.addExpense(1500, "Shopping");

        // Display summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Show total accounts (static variable shared)
        System.out.println("\nTotal Accounts Created: " + PersonalAccount.getTotalAccounts());
    }
}
