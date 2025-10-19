// ...existing code...
import java.util.HashSet;
import java.util.Objects;

class Employee {
    private final String empCode;
    private String name;

    public Employee(String empCode, String name) {
        this.empCode = empCode;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return Objects.equals(empCode, other.empCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(empCode);
    }

    @Override
    public String toString() {
        return empCode + " - " + name;
    }
}

public class EmployeeAuth {
    public static void main(String[] args) {
        Employee e1 = new Employee("BL001", "Ritika");
        Employee e2 = new Employee("BL001", "Ritika S.");

        System.out.println("e1 == e2? " + (e1 == e2));
        System.out.println("e1.equals(e2)? " + e1.equals(e2));
        
        HashSet<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println("Set size: " + set.size()); // Should be 1 if equals/hashCode is correct
    }
}
// ...existing code...