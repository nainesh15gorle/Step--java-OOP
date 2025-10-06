public abstract class Employee {
    protected String name;
    protected double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public abstract double calculateBonus();
    
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + salary);
        System.out.println("Bonus: $" + calculateBonus());
        System.out.println("Total: $" + (salary + calculateBonus()));
    }
}
public class Manager extends Employee {
    public Manager(String name, double salary) {
        super(name, salary);
    }
    
    @Override
    public double calculateBonus() {
        return salary * 0.20;  // 20% bonus for managers
    }
}
public class Developer extends Employee {
    public Developer(String name, double salary) {
        super(name, salary);
    }
    
    @Override
    public double calculateBonus() {
        return salary * 0.10;  // 10% bonus for developers
    }
}
public class EmployeeTest {
    public static void main(String[] args) {
        Employee manager = new Manager("Alice", 50000);
        Employee developer = new Developer("Bob", 40000);
        
        System.out.println("Manager Details:");
        manager.displayDetails();
        
        System.out.println("\nDeveloper Details:");
        developer.displayDetails();
    }
}
