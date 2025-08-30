public class VehicleRentalSystem {
    public static void main(String[] args) {
        // Set company name using static method
        Vehicle.setCompanyName("Speedy Rentals");
        
        // Create multiple vehicle objects
        Vehicle car1 = new Vehicle("V001", "Toyota", "Camry", 50.0);
        Vehicle car2 = new Vehicle("V002", "Honda", "Civic", 45.0);
        Vehicle suv1 = new Vehicle("V003", "Ford", "Explorer", 75.0);
        
        System.out.println("=== INITIAL VEHICLE INFORMATION ===");
        car1.displayVehicleInfo();
        car2.displayVehicleInfo();
        suv1.displayVehicleInfo();
        
        System.out.println("\n=== DEMONSTRATING INSTANCE VS STATIC MEMBERS ===");
        
        // Rent vehicles - instance methods affect individual objects
        System.out.println("Renting vehicles...");
        double rent1 = car1.rentVehicle(3);
        double rent2 = car2.rentVehicle(5);
        double rent3 = suv1.rentVehicle(2);
        
        System.out.println("Rental amounts: Car1: $" + rent1 + ", Car2: $" + rent2 + ", SUV: $" + rent3);
        
        // Show how static variables are shared across all objects
        System.out.println("\n=== STATIC VARIABLES (SHARED ACROSS ALL OBJECTS) ===");
        System.out.println("Total Vehicles: " + Vehicle.getTotalVehicles());
        System.out.println("Total Revenue: $" + Vehicle.getTotalRevenue());
        System.out.println("Total Rental Days: " + Vehicle.getTotalRentalDays());
        System.out.println("Average Rent per Day: $" + Vehicle.getAverageRentPerDay());
        
        // Return vehicles
        System.out.println("\nReturning vehicles...");
        car1.returnVehicle();
        car2.returnVehicle();
        
        // Rent again to show static counters updating
        System.out.println("\nRenting vehicles again...");
        car1.rentVehicle(4);
        suv1.rentVehicle(7);
        
        // Display updated static statistics
        System.out.println("\n=== UPDATED STATISTICS ===");
        Vehicle.displayCompanyStats();
        
        System.out.println("\n=== INDIVIDUAL VEHICLE INFORMATION ===");
        car1.displayVehicleInfo();
        car2.displayVehicleInfo();
        suv1.displayVehicleInfo();
        
        // Demonstrate instance variables are unique to each object
        System.out.println("\n=== INSTANCE VARIABLES (UNIQUE TO EACH OBJECT) ===");
        System.out.println("Car1 Available: " + car1.isAvailable());
        System.out.println("Car2 Available: " + car2.isAvailable());
        System.out.println("SUV Available: " + suv1.isAvailable());
        System.out.println("Car1 Daily Rent: $" + car1.getRentPerDay());
        System.out.println("Car2 Daily Rent: $" + car2.getRentPerDay());
    }
}

class Vehicle {
    // Instance variables (unique to each object)
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int totalRentals;
    private int totalDaysRented;
    
    // Static variables (shared across all objects)
    private static int totalVehicles = 0;
    private static double totalRevenue = 0.0;
    private static String companyName = "Default Rentals";
    private static int totalRentalDays = 0;
    
    // Constructor
    public Vehicle(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.totalRentals = 0;
        this.totalDaysRented = 0;
        
        // Update static counter
        totalVehicles++;
    }
    
    // Instance method to rent vehicle
    public double rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is not available for rent!");
            return 0.0;
        }
        
        double rentAmount = calculateRent(days);
        isAvailable = false;
        totalRentals++;
        totalDaysRented += days;
        
        System.out.println("Vehicle " + vehicleId + " rented for " + days + " days. Amount: $" + rentAmount);
        return rentAmount;
    }
    
    // Instance method to return vehicle
    public void returnVehicle() {
        if (isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is already available!");
            return;
        }
        
        isAvailable = true;
        System.out.println("Vehicle " + vehicleId + " returned successfully.");
    }
    
    // Instance method to calculate rent and update static revenue
    public double calculateRent(int days) {
        double rentAmount = rentPerDay * days;
        
        // Update static revenue counter
        totalRevenue += rentAmount;
        totalRentalDays += days;
        
        return rentAmount;
    }
    
    // Instance method to display vehicle information
    public void displayVehicleInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent per Day: $" + rentPerDay);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Total Rentals: " + totalRentals);
        System.out.println("Total Days Rented: " + totalDaysRented);
        System.out.println("----------------------------------------");
    }
    
    // Static methods
    public static void setCompanyName(String name) {
        companyName = name;
    }
    
    public static double getTotalRevenue() {
        return totalRevenue;
    }
    
    public static int getTotalVehicles() {
        return totalVehicles;
    }
    
    public static int getTotalRentalDays() {
        return totalRentalDays;
    }
    
    public static double getAverageRentPerDay() {
        if (totalRentalDays == 0) return 0.0;
        return totalRevenue / totalRentalDays;
    }
    
    public static void displayCompanyStats() {
        System.out.println("=== " + companyName + " STATISTICS ===");
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
        System.out.println("Total Rental Days: " + totalRentalDays);
        System.out.println("Average Rent per Day: $" + String.format("%.2f", getAverageRentPerDay()));
    }
    
    // Getter methods for instance variables
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public double getRentPerDay() {
        return rentPerDay;
    }
    
    public String getVehicleId() {
        return vehicleId;
    }
}