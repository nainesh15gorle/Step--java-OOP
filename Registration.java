// File: Registration.java

class ContactInfo implements Cloneable {
    String email, phone;
    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow copy
    }
}

class Student implements Cloneable {
    String id, name;
    ContactInfo contact;

    public Student(String id, String name, ContactInfo contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // Shallow copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Deep copy
    public Student deepClone() throws CloneNotSupportedException {
        ContactInfo clonedContact = (ContactInfo) contact.clone();
        return new Student(id, name, clonedContact);
    }
}

public class Registration {
    public static void main(String[] args) throws CloneNotSupportedException {
        ContactInfo c1 = new ContactInfo("abc@example.com", "1234");
        Student s1 = new Student("101", "Rhea", c1);
        Student s2 = (Student) s1.clone();              // shallow copy
        Student s3 = s1.deepClone();                    // deep copy

        s1.contact.email = "xyz@example.com";
        System.out.println("Shallow copy email: " + s2.contact.email); // Will change
        System.out.println("Deep copy email: " + s3.contact.email);    // Will not change
    }
}
