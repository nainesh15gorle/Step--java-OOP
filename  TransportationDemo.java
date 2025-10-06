abstract class Vehicle {
    protected String vehicleId;
    protected String location;
    
    public Vehicle(String vehicleId, String location) {
        this.vehicleId = vehicleId;
        this.location = location;
    }
    
    // Method to be overridden - demonstrates dynamic method dispatch
    public abstract void dispatch(String destination);
    public abstract void displayStatus();
}

class Bus extends Vehicle {
    private String route;
    private int currentPassengers;
    private int maxCapacity;
    
    public Bus(String vehicleId, String location, String route, int maxCapacity) {
        super(vehicleId, location);
        this.route = route;
        this.maxCapacity = maxCapacity;
        this.currentPassengers = 0;
    }
    
    @Override
    public void dispatch(String destination) {
        System.out.println("=== BUS DISPATCH ===");
        System.out.println("Bus " + vehicleId + " dispatched!");
        System.out.println("Route: " + route);
        System.out.println("From: " + location + " To: " + destination);
        System.out.println("Following fixed route with scheduled stops");
        displayStatus();
        System.out.println("===================\n");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("Passengers: " + currentPassengers + "/" + maxCapacity);
        System.out.println("Capacity Available: " + (maxCapacity - currentPassengers));
    }
    
    public void boardPassengers(int count) {
        currentPassengers = Math.min(currentPassengers + count, maxCapacity);
    }
}

class Taxi extends Vehicle {
    private boolean available;
    private double fareRate;
    
    public Taxi(String vehicleId, String location, double fareRate) {
        super(vehicleId, location);
        this.fareRate = fareRate;
        this.available = true;
    }
    
    @Override
    public void dispatch(String destination) {
        System.out.println("=== TAXI DISPATCH ===");
        System.out.println("Taxi " + vehicleId + " dispatched!");
        System.out.println("Door-to-door service");
        System.out.println("From: " + location + " To: " + destination);
        double distance = calculateDistance(destination);
        double fare = distance * fareRate;
        System.out.println("Distance: " + distance + " km");
        System.out.println("Estimated Fare: $" + String.format("%.2f", fare));
        available = false;
        displayStatus();
        System.out.println("====================\n");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("Status: " + (available ? "Available" : "Occupied"));
        System.out.println("Rate: $" + fareRate + "/km");
    }
    
    private double calculateDistance(String destination) {
        // Simulated distance calculation
        return Math.random() * 20 + 5; // 5-25 km range
    }
}

class Train extends Vehicle {
    private String schedule;
    private int carCount;
    private int passengersPerCar;
    
    public Train(String vehicleId, String location, String schedule, int carCount, int passengersPerCar) {
        super(vehicleId, location);
        this.schedule = schedule;
        this.carCount = carCount;
        this.passengersPerCar = passengersPerCar;
    }
    
    @Override
    public void dispatch(String destination) {
        System.out.println("=== TRAIN DISPATCH ===");
        System.out.println("Train " + vehicleId + " dispatched!");
        System.out.println("Operating on schedule: " + schedule);
        System.out.println("From: " + location + " To: " + destination);
        System.out.println("Multiple stops along railway line");
        displayStatus();
        System.out.println("=====================\n");
    }
    
    @Override
    public void displayStatus() {
        int totalCapacity = carCount * passengersPerCar;
        System.out.println("Cars: " + carCount);
        System.out.println("Total Capacity: " + totalCapacity + " passengers");
        System.out.println("Schedule: " + schedule);
    }
}

class Bike extends Vehicle {
    private boolean electricAssist;
    private double batteryLevel;
    
    public Bike(String vehicleId, String location, boolean electricAssist) {
        super(vehicleId, location);
        this.electricAssist = electricAssist;
        this.batteryLevel = electricAssist ? 100.0 : 0.0;
    }
    
    @Override
    public void dispatch(String destination) {
        System.out.println("=== BIKE DISPATCH ===");
        System.out.println("Bike " + vehicleId + " dispatched!");
        System.out.println("Eco-friendly short-distance transport");
        System.out.println("From: " + location + " To: " + destination);
        System.out.println("Perfect for short trips and avoiding traffic");
        displayStatus();
        System.out.println("====================\n");
    }
    
    @Override
    public void displayStatus() {
        System.out.println("Type: " + (electricAssist ? "E-Bike" : "Manual Bike"));
        if (electricAssist) {
            System.out.println("Battery: " + batteryLevel + "%");
        }
        System.out.println("Carbon Footprint: Zero");
    }
}

// Dispatch System demonstrating Dynamic Method Dispatch
class TransportDispatchSystem {
    public void dispatchVehicle(Vehicle vehicle, String destination) {
        // This method calls the appropriate dispatch method based on the actual object type
        // This is Dynamic Method Dispatch in action!
        vehicle.dispatch(destination);
    }
    
    public void dispatchFleet(Vehicle[] fleet, String destination) {
        System.out.println("DISPATCHING ENTIRE FLEET TO: " + destination);
        System.out.println("============================================");
        for (Vehicle vehicle : fleet) {
            dispatchVehicle(vehicle, destination);
        }
    }
}

public class TransportationDemo {
    public static void main(String[] args) {
        // Create different types of vehicles
        Vehicle[] fleet = {
            new Bus("BUS001", "Downtown", "Route 15", 50),
            new Taxi("TAXI205", "Airport", 2.5),
            new Train("TRAIN001", "Central Station", "Express 9:00 AM", 8, 100),
            new Bike("BIKE042", "Park Avenue", true)
        };
        
        TransportDispatchSystem dispatchSystem = new TransportDispatchSystem();
        
        // Demonstrate dynamic method dispatch
        dispatchSystem.dispatchFleet(fleet, "City Center");
    }
}
