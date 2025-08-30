// Base class with reusable code
class Vehicle {
    protected String brand;
    protected String model;
    
    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
    
    // Reusable methods
    public void start() {
        System.out.println(brand + " " + model + " starting...");
    }
    
    public void stop() {
        System.out.println(brand + " " + model + " stopped");
    }
}

// Subclass reusing base class code
class Car extends Vehicle {
    private int doors;
    
    public Car(String brand, String model, int doors) {
        super(brand, model); // Reuse base constructor
        this.doors = doors;
    }
    
    // Additional functionality
    public void honk() {
        System.out.println(brand + " " + model + " honking!");
    }
    
    @Override
    public void start() {
        super.start(); // Reuse base method
        System.out.println("Car specific startup");
    }
}

// Demo
public class OOPDemo {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Camry", 4);
        car.start();  // Reused from Vehicle
        car.honk();   // Specific to Car
        car.stop();   // Reused from Vehicle
        
        System.out.println("\nBenefits:");
        System.out.println("1. No code duplication");
        System.out.println("2. Easy maintenance");
        System.out.println("3. Extensible design");
    }
}