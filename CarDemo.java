// Class representing a real-world Car
class Car {
    // Attributes (state)
    private String brand;
    private String model;
    private int year;
    private double currentSpeed;
    
    // Constructor to initialize objects
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.currentSpeed = 0;
    }
    
    // Methods (behavior)
    public void accelerate(double speedIncrease) {
        currentSpeed += speedIncrease;
        System.out.println(brand + " " + model + " accelerated to " + currentSpeed + " km/h");
    }
    
    public void brake(double speedDecrease) {
        currentSpeed = Math.max(0, currentSpeed - speedDecrease);
        System.out.println(brand + " " + model + " slowed down to " + currentSpeed + " km/h");
    }
    
    public void displayInfo() {
        System.out.println(brand + " " + model + " (" + year + ") - Current speed: " + currentSpeed + " km/h");
    }
    
    // Getter methods
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
}

// Main class to demonstrate objects
public class CarDemo {
    public static void main(String[] args) {
        // Creating objects (instances) of Car class
        Car myCar = new Car("Toyota", "Camry", 2023);
        Car sportsCar = new Car("Porsche", "911", 2024);
        
        // Using object methods
        System.out.println("=== Car Demonstration ===");
        
        myCar.displayInfo();
        myCar.accelerate(60);
        myCar.accelerate(20);
        myCar.brake(30);
        
        System.out.println("\n--- Sports Car ---");
        sportsCar.displayInfo();
        sportsCar.accelerate(120);
        sportsCar.brake(50);
        
        // Accessing object properties
        System.out.println("\n=== Car Details ===");
        System.out.println("My car: " + myCar.getBrand() + " " + myCar.getModel());
        System.out.println("Sports car: " + sportsCar.getBrand() + " " + sportsCar.getModel());
    }
}