public abstract class Vehicle {
    public abstract void start();
    public void fuelType() {
        System.out.println("Uses fuel");
    }
}
public class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car starts with key");
    }
}
public class Bike extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bike starts with kick");
    }
}
public class VehicleTest {
    public static void main(String[] args) {
        Vehicle v1 = new Car();
        v1.start();
        v1.fuelType();
        Vehicle v2 = new Bike();
        v2.start();
        v2.fuelType();
    }
}
