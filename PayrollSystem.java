import java.util.ArrayList;
import java.util.List;

class Employee {
    // Private variables
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    
    // Static variable to track total employees
    private static int totalEmployees = 0;
    private static List<Employee> allEmployees = new ArrayList<>();
    
    // Constructor for Full-time employees
    public Employee(String empId, String empName, String department, double baseSalary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-time";
        totalEmployees++;
        allEmployees.add(this);
    }
    
    // Constructor for Part-time employees
    public Employee(String empId, String empName, String department, double hourlyRate, int hoursWorked) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hoursWorked;
        this.empType = "Part-time";
        totalEmployees++;
        allEmployees.add(this);
    }
    
    // Constructor for Contract employees
    public Employee(String empId, String empName, String department, double fixedAmount, String contractType) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = fixedAmount;
        this.empType = "Contract (" + contractType + ")";
        totalEmployees++;
        allEmployees.add(this);
    }
    
    // Overloaded calculateSalary methods
    public double calculateSalary(double bonus) {
        // For full-time employees: base salary + bonus
        return baseSalary + bonus;
    }
    
    public double calculateSalary(double hourlyRate, int hoursWorked) {
        // For part-time employees: hourly rate × hours
        return hourlyRate * hoursWorked;
    }
    
    public double calculateSalary() {
        // For contract employees: fixed amount
        return baseSalary;
    }
    
    // Overloaded calculateTax methods
    public double calculateTax(double bonus) {
        // Full-time: 20% tax on (base salary + bonus)
        double totalSalary = calculateSalary(bonus);
        return totalSalary * 0.20;
    }
    
    public double calculateTax(double hourlyRate, int hoursWorked) {
        // Part-time: 15% tax
        double totalSalary = calculateSalary(hourlyRate, hoursWorked);
        return totalSalary * 0.15;
    }
    
    public double calculateTax() {
        // Contract: 10% tax
        return baseSalary * 0.10;
    }
    
    // Generate pay slip
    public void generatePaySlip(double... params) {
        double salary = 0;
        double tax = 0;
        
        if (empType.equals("Full-time") && params.length == 1) {
            salary = calculateSalary(params[0]);
            tax = calculateTax(params[0]);
        } else if (empType.startsWith("Part-time") && params.length == 2) {
            salary = calculateSalary(params[0], (int)params[1]);
            tax = calculateTax(params[0], (int)params[1]);
        } else if (empType.startsWith("Contract")) {
            salary = calculateSalary();
            tax = calculateTax();
        }
        
        System.out.println("=== PAY SLIP ===");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);
        System.out.println("Salary: $" + String.format("%.2f", salary));
        System.out.println("Tax: $" + String.format("%.2f", tax));
        System.out.println("Net Pay: $" + String.format("%.2f", (salary - tax)));
        System.out.println("================\n");
    }
    
    // Display employee information
    public void displayEmployeeInfo() {
        System.out.println("ID: " + empId + " | Name: " + empName + 
                          " | Dept: " + department + " | Type: " + empType);
    }
    
    // Static methods
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    
    public static void generateCompanyPayrollReport() {
        System.out.println("=== COMPANY PAYROLL REPORT ===");
        System.out.println("Total Employees: " + totalEmployees);
        System.out.println("Employee List:");
        System.out.println("----------------------------------------");
        
        for (Employee emp : allEmployees) {
            emp.displayEmployeeInfo();
        }
        System.out.println("========================================\n");
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        // Create different types of employees
        Employee fullTimeEmp = new Employee("FT001", "John Doe", "IT", 5000);
        Employee partTimeEmp = new Employee("PT001", "Jane Smith", "HR", 25.0, 80);
        Employee contractEmp = new Employee("CT001", "Bob Johnson", "Finance", 3000, "6-month");
        
        // Demonstrate method overloading
        System.out.println("=== METHOD OVERLOADING DEMONSTRATION ===");
        
        // Full-time employee calculations
        System.out.println("Full-time Employee:");
        double fullTimeSalary = fullTimeEmp.calculateSalary(1000); // bonus = 1000
        double fullTimeTax = fullTimeEmp.calculateTax(1000);
        System.out.println("Salary: $" + fullTimeSalary + " | Tax: $" + fullTimeTax);
        
        // Part-time employee calculations
        System.out.println("\nPart-time Employee:");
        double partTimeSalary = partTimeEmp.calculateSalary(25.0, 80); // hourly rate, hours
        double partTimeTax = partTimeEmp.calculateTax(25.0, 80);
        System.out.println("Salary: $" + partTimeSalary + " | Tax: $" + partTimeTax);
        
        // Contract employee calculations
        System.out.println("\nContract Employee:");
        double contractSalary = contractEmp.calculateSalary();
        double contractTax = contractEmp.calculateTax();
        System.out.println("Salary: $" + contractSalary + " | Tax: $" + contractTax);
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Generate pay slips
        fullTimeEmp.generatePaySlip(1000); // bonus
        partTimeEmp.generatePaySlip(25.0, 80); // hourly rate, hours
        contractEmp.generatePaySlip(); // no parameters
        
        // Display company-wide report
        Employee.generateCompanyPayrollReport();
        
        // Show total employees
        System.out.println("Total Employees in Company: " + Employee.getTotalEmployees());
    }
}