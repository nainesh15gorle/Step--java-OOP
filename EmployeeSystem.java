import java.util.*;

abstract class Employee {
    String empId, empName, dept, designation;
    double baseSalary;
    boolean[] attendance = new boolean[30];
    static int totalEmployees = 0, workingDays = 30;
    static String companyName = "TechCorp";
    static double totalSalaryExpense = 0;

    Employee(String id, String name, String d, String des, double sal) {
        empId=id; empName=name; dept=d; designation=des; baseSalary=sal; totalEmployees++;
    }

    void markAttendance(int day, boolean present) { attendance[day-1] = present; }

    int getPresentDays() {
        int c=0; for(boolean b:attendance) if(b) c++; return c;
    }

    abstract double calculateSalary();

    double calculateBonus() { return getPresentDays() > 25 ? baseSalary*0.1 : 0; }

    void generatePaySlip() {
        double salary = calculateSalary() + calculateBonus();
        totalSalaryExpense += salary;
        System.out.println(empName + " | Salary: " + salary);
    }
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String id, String n, String d, String des, double sal) {
        super(id,n,d,des,sal);
    }
    double calculateSalary() { return baseSalary; }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String id, String n, String d, String des, double sal) {
        super(id,n,d,des,sal);
    }
    double calculateSalary() { return baseSalary * 0.5; }
}

class ContractEmployee extends Employee {
    ContractEmployee(String id, String n, String d, String des, double sal) {
        super(id,n,d,des,sal);
    }
    double calculateSalary() { return baseSalary * 0.8; }
}

class Department {
    String deptId, deptName;
    Employee manager;
    ArrayList<Employee> employees = new ArrayList<>();
    double budget;

    Department(String id, String n, Employee m, double b) {
        deptId=id; deptName=n; manager=m; budget=b; employees.add(m);
    }

    void addEmployee(Employee e){ employees.add(e); }

    double getExpenses() {
        double sum=0; for(Employee e:employees) sum+=e.baseSalary; return sum;
    }
}

public class EmployeeSystem {
    public static void main(String[] args) {
        Employee e1 = new FullTimeEmployee("E1","Alice","IT","Dev",50000);
        Employee e2 = new PartTimeEmployee("E2","Bob","HR","Asst",20000);
        Employee e3 = new ContractEmployee("E3","Charlie","IT","Tester",30000);

        Department it = new Department("D1","IT",e1,200000);
        it.addEmployee(e3);

        e1.markAttendance(1,true); e2.markAttendance(1,true); e3.markAttendance(1,false);

        e1.generatePaySlip(); e2.generatePaySlip(); e3.generatePaySlip();

        System.out.println("Total Employees: " + Employee.totalEmployees);
        System.out.println("Company Payroll: " + Employee.totalSalaryExpense);
        System.out.println("IT Dept Expense: " + it.getExpenses());
    }
}
