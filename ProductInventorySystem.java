import java.util.*;

public class ProductInventorySystem {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", 899.99, 15, "TechSuppliers Inc", "Electronics"),
            new Product("P002", "Smartphone", 499.99, 8, "MobileTech Ltd", "Electronics"),
            new Product("P003", "Office Chair", 199.99, 25, "FurnitureWorld", "Furniture"),
            new Product("P004", "Wireless Mouse", 29.99, 5, "TechSuppliers Inc", "Electronics"),
            new Product("P005", "Desk Lamp", 49.99, 12, "HomeGoods Corp", "Home")
        };

        System.out.println("=== INITIAL INVENTORY ===");
        Product.generateReport(products);

        System.out.println("\n=== INVENTORY OPERATIONS ===");
        products[1].addStock(10);
        products[3].addStock(15);
        products[0].reduceStock(5);
        products[2].reduceStock(30);
        products[0].updatePrice(949.99);
        products[4].updatePrice(39.99);

        System.out.println("\n=== UPDATED INVENTORY ===");
        Product.generateReport(products);

        System.out.println("\n=== SEARCH RESULTS ===");
        Product found = Product.search(products, "Smartphone");
        if (found != null) found.displayInfo();

        System.out.println("\n=== LOW STOCK ITEMS ===");
        Product.findLowStock(products);
    }
}

class Product {
    private String id, name, supplier, category;
    private double price;
    private int quantity;
    
    private static int totalProducts = 0;
    private static double totalValue = 0;
    private static int lowStockCount = 0;
    private static Set<String> categories = new HashSet<>();

    public Product(String id, String name, double price, int quantity, String supplier, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
        this.category = category;
        
        totalProducts++;
        totalValue += price * quantity;
        if (quantity < 10) lowStockCount++;
        categories.add(category);
    }

    public void addStock(int qty) {
        if (qty <= 0) return;
        updateTotalValue(-price * quantity);
        quantity += qty;
        updateTotalValue(price * quantity);
        updateLowStock();
        System.out.println("Added " + qty + " to " + name + " → " + quantity);
    }

    public void reduceStock(int qty) {
        if (qty <= 0 || qty > quantity) {
            System.out.println("Cannot reduce " + qty + " from " + name + " (Available: " + quantity + ")");
            return;
        }
        updateTotalValue(-price * quantity);
        quantity -= qty;
        updateTotalValue(price * quantity);
        updateLowStock();
        System.out.println("Reduced " + qty + " from " + name + " → " + quantity);
    }

    public void updatePrice(double newPrice) {
        updateTotalValue(-price * quantity);
        price = newPrice;
        updateTotalValue(price * quantity);
        System.out.println(name + " price updated: $" + price);
    }

    public boolean isLowStock() { return quantity < 10; }
    public double getValue() { return price * quantity; }
    
    public void displayInfo() {
        System.out.printf("%s: %s | $%.2f | %d units | %s | %s%n", 
            id, name, price, quantity, supplier, category);
    }

    private void updateTotalValue(double change) { totalValue += change; }
    
    private void updateLowStock() {
        boolean wasLow = quantity < 10;
        boolean isLow = quantity < 10;
        if (wasLow && !isLow) lowStockCount--;
        else if (!wasLow && isLow) lowStockCount++;
    }

    public static void generateReport(Product[] products) {
        System.out.printf("Total Products: %d | Total Value: $%.2f | Low Stock: %d%n", 
            totalProducts, totalValue, lowStockCount);
        System.out.println("Categories: " + categories);
        for (Product p : products) p.displayInfo();
    }

    public static void findLowStock(Product[] products) {
        for (Product p : products) 
            if (p.isLowStock()) 
                System.out.println(p.name + " (" + p.quantity + " units)");
    }

    public static Product search(Product[] products, String name) {
        for (Product p : products) 
            if (p.name.equalsIgnoreCase(name)) 
                return p;
        return null;
    }

    // Getters
    public static int getTotalProducts() { return totalProducts; }
    public static double getTotalValue() { return totalValue; }
    public static int getLowStockCount() { return lowStockCount; }
}