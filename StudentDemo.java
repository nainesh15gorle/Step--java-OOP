// Student class representing a real-world student
class Student {
    // Private fields for encapsulation
    private final String id;
    private final String name;
    private int age;
    private double gpa;
    
    // Constructor with validation
    public Student(String id, String name, int age, double gpa) {
        this.id = validateId(id);
        this.name = validateName(name);
        this.age = validateAge(age);
        this.gpa = validateGpa(gpa);
    }
    
    // Validation methods
    private String validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return id.trim();
    }
    
    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return name.trim();
    }
    
    private int validateAge(int age) {
        if (age < 5 || age > 120) {
            throw new IllegalArgumentException("Age must be between 5 and 120");
        }
        return age;
    }
    
    private double validateGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        return Math.round(gpa * 100.0) / 100.0; // Round to 2 decimal places
    }
    
    // Getter methods (no setters for immutable fields)
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }
    
    // Setter methods with validation for mutable fields
    public void setAge(int age) { this.age = validateAge(age); }
    public void setGpa(double gpa) { this.gpa = validateGpa(gpa); }
    
    // Business methods
    public boolean isHonorStudent() {
        return gpa >= 3.5;
    }
    
    public String getAcademicStatus() {
        if (gpa >= 3.5) return "Honors";
        if (gpa >= 3.0) return "Good Standing";
        if (gpa >= 2.0) return "Satisfactory";
        return "Academic Probation";
    }
    
    // Override toString for meaningful string representation
    @Override
    public String toString() {
        return String.format("Student[ID: %s, Name: %s, Age: %d, GPA: %.2f, Status: %s]",
                           id, name, age, gpa, getAcademicStatus());
    }
    
    // Override equals and hashCode for proper object comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id.equals(student.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

// Demo class to test Student functionality
public class StudentDemo {
    public static void main(String[] args) {
        try {
            // Create student objects
            Student student1 = new Student("S001", "Alice Johnson", 20, 3.8);
            Student student2 = new Student("S002", "Bob Smith", 22, 2.9);
            Student student3 = new Student("S003", "Carol Davis", 19, 3.2);
            
            // Display student information
            System.out.println("=== Student Information ===");
            System.out.println(student1);
            System.out.println(student2);
            System.out.println(student3);
            
            // Demonstrate method usage
            System.out.println("\n=== Academic Status ===");
            System.out.printf("%s is honor student: %b%n", 
                student1.getName(), student1.isHonorStudent());
            System.out.printf("%s's status: %s%n", 
                student2.getName(), student2.getAcademicStatus());
            
            // Update student GPA
            System.out.println("\n=== GPA Update ===");
            student2.setGpa(3.1);
            System.out.println("Updated: " + student2);
            System.out.printf("New status: %s%n", student2.getAcademicStatus());
            
            // Demonstrate object comparison
            System.out.println("\n=== Object Comparison ===");
            Student student1Copy = new Student("S001", "Alice Johnson", 20, 3.8);
            System.out.printf("student1.equals(student1Copy): %b%n", 
                student1.equals(student1Copy));
            System.out.printf("student1.equals(student2): %b%n", 
                student1.equals(student2));
                
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating student: " + e.getMessage());
        }
    }
}