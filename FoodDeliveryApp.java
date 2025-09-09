import java.util.HashMap;
import java.util.Map;

public class FoodOrder {
    // Fields
    private String customerName;
    private String foodItem;
    private int quantity;
    private double price;
    
    // Constants for default values
    private static final String DEFAULT_CUSTOMER = "Unknown";
    private static final String DEFAULT_FOOD = "Burger";
    private static final int DEFAULT_QUANTITY = 1;
    private static final double DEFAULT_PRICE = 199.0;
    
    // Menu with fixed prices
    private static final Map<String, Double> MENU = new HashMap<>();
    static {
        MENU.put("Burger", 199.0);
        MENU.put("Pizza", 299.0);
        MENU.put("Pasta", 179.0);
        MENU.put("Sandwich", 149.0);
        MENU.put("Fries", 99.0);
        MENU.put("Coke", 49.0);
        MENU.put("Ice Cream", 129.0);
        MENU.put("Salad", 159.0);
    }
    
    // 1. Default constructor → assigns "Unknown" order
    public FoodOrder() {
        this.customerName = DEFAULT_CUSTOMER;
        this.foodItem = DEFAULT_FOOD;
        this.quantity = DEFAULT_QUANTITY;
        this.price = DEFAULT_PRICE;
    }
    
    // 2. Constructor with food item → sets quantity = 1, price = default
    public FoodOrder(String foodItem) {
        this(); // Call default constructor first
        this.foodItem = foodItem;
        this.price = getItemPrice(foodItem);
    }
    
    // 3. Constructor with food item and quantity → calculates price = quantity × fixedRate
    public FoodOrder(String foodItem, int quantity) {
        this.customerName = DEFAULT_CUSTOMER;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = calculateTotalPrice(foodItem, quantity);
    }
    
    // 4. Full constructor with all details
    public FoodOrder(String customerName, String foodItem, int quantity) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = calculateTotalPrice(foodItem, quantity);
    }
    
    // Helper method to get item price from menu
    private double getItemPrice(String item) {
        return MENU.getOrDefault(item, DEFAULT_PRICE);
    }
    
    // Helper method to calculate total price
    private double calculateTotalPrice(String item, int qty) {
        double itemPrice = getItemPrice(item);
        return itemPrice * qty;
    }
    
    // Method to print bill
    public void printBill() {
        System.out.println("🍔 Food Order Bill:");
        System.out.println("─────────────────────────");
        System.out.println("Customer:     " + customerName);
        System.out.println("Food Item:    " + foodItem);
        System.out.println("Quantity:     " + quantity);
        System.out.println("Price:        ₹" + price);
        System.out.println("─────────────────────────");
        System.out.println();
    }
    
    // Method to print detailed bill with itemized pricing
    public void printDetailedBill() {
        double itemPrice = getItemPrice(foodItem);
        
        System.out.println("📋 Detailed Order Bill:");
        System.out.println("─────────────────────────");
        System.out.println("Customer:      " + customerName);
        System.out.println("─────────────────────────");
        System.out.printf("%-15s %3s %8s %8s%n", "Item", "Qty", "Price", "Total");
        System.out.println("─────────────────────────");
        System.out.printf("%-15s %3d %8.2f %8.2f%n", foodItem, quantity, itemPrice, price);
        System.out.println("─────────────────────────");
        System.out.printf("%-15s %16.2f%n", "Total:", price);
        System.out.println("─────────────────────────");
        System.out.println();
    }
    
    // Method to add more quantity
    public void addQuantity(int additionalQty) {
        if (additionalQty > 0) {
            quantity += additionalQty;
            price = calculateTotalPrice(foodItem, quantity);
            System.out.println("➕ Added " + additionalQty + " more " + foodItem + "(s)");
        }
    }
    
    // Method to change food item
    public void changeItem(String newFoodItem, int newQuantity) {
        this.foodItem = newFoodItem;
        this.quantity = newQuantity;
        this.price = calculateTotalPrice(newFoodItem, newQuantity);
        System.out.println("🔄 Changed order to: " + newQuantity + " x " + newFoodItem);
    }
    
    // Getters
    public String getCustomerName() {
        return customerName;
    }
    
    public String getFoodItem() {
        return foodItem;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setters
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
        this.price = calculateTotalPrice(foodItem, quantity);
    }
    
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
            this.price = calculateTotalPrice(foodItem, quantity);
        }
    }
    
    // Static method to display menu
    public static void displayMenu() {
        System.out.println("📜 Restaurant Menu:");
        System.out.println("─────────────────────────");
        System.out.printf("%-15s %10s%n", "Item", "Price (₹)");
        System.out.println("─────────────────────────");
        for (Map.Entry<String, Double> entry : MENU.entrySet()) {
            System.out.printf("%-15s %10.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("─────────────────────────");
        System.out.println();
    }
    
    // Static method to get menu items
    public static String[] getMenuItems() {
        return MENU.keySet().toArray(new String[0]);
    }
}

// Main class to test the Food Delivery System
public class FoodDeliveryApp {
    public static void main(String[] args) {
        System.out.println("🚀 Welcome to Food Delivery System! 🚀\n");
        
        // Display menu
        FoodOrder.displayMenu();
        
        // Create orders using different constructors
        FoodOrder[] orders = {
            // 1. Default constructor
            new FoodOrder(),
            
            // 2. Constructor with food item only
            new FoodOrder("Pizza"),
            new FoodOrder("Pasta"),
            
            // 3. Constructor with food item and quantity
            new FoodOrder("Burger", 2),
            new FoodOrder("Fries", 3),
            new FoodOrder("Coke", 4),
            
            // 4. Full constructor with customer name
            new FoodOrder("Alice", "Pizza", 1),
            new FoodOrder("Bob", "Burger", 2),
            new FoodOrder("Charlie",   "Sandwich", 1),  
            new FoodOrder("Diana", "Ice Cream", 2)
        };
        // Print bills for all orders
        for (FoodOrder order : orders) {
            order.printBill();
            order.printDetailedBill();
        }
        // Modify some orders
        orders[0].setCustomerName("Eve");
        orders[0].changeItem("Salad", 2);

        orders[3].addQuantity(1); // Add 1 more Burger to Alice's order
        orders[6].setQuantity(3); // Change Bob's Burger quantity to 3
        // ...existing code...

        orders[3].addQuantity(1); // Add 1 more Burger to Alice's order
    }
}