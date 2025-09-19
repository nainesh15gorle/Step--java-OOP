import java.util.Random;

public class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;
    private String registrationNumber;
    private boolean isRunning;

    public Vehicle() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 2000;
        this.engineType = "Petrol";
        this.isRunning = false;
        this.registrationNumber = generateRandomRegistration();
        System.out.println("Vehicle default constructor called");
    }

    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.isRunning = false;
        this.registrationNumber = generateRandomRegistration();
        System.out.println("Vehicle parameterized constructor called");
    }

    private String generateRandomRegistration() {
        return "REG" + (new Random().nextInt(90000) + 10000);
    }

    public void start() {
        this.isRunning = true;
        System.out.println("Vehicle started");
    }

    public void stop() {
        this.isRunning = false;
        System.out.println("Vehicle stopped");
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getVehicleInfo() {
        return brand + " " + model + ", " + year + ": " + engineType +
               " | Reg No: " + registrationNumber;
    }

    public void displaySpecs() {
        System.out.println("--Vehicle Specs--\nBrand: " + brand +
                           "\nModel: " + model +
                           "\nYear: " + year +
                           "\nEngine: " + engineType);
    }
}
// Vehicle.java
// Represents a generic vehicle with basic attributes and behaviors.

// Attributes: brand, model, year, engineType, registrationNumber, isRunning
// Constructors: default and parameterized
// Methods: start, stop, getRegistrationNumber, setRegistrationNumber,


//          isRunning, getVehicleInfo, displaySpecs
// Access Modifiers: protected for attributes, private for registrationNumber and isRunning

// Example Usage:
// Vehicle myCar = new Vehicle("Toyota", "Corolla", 2020, " 

