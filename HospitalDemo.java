abstract class MedicalStaff {
    protected String staffId;
    protected String name;
    protected String department;
    protected String shift;
    protected double salary;
    
    public MedicalStaff(String staffId, String name, String department, String shift, double salary) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
    }
    
    // Common operations for all medical staff
    public void clockIn() {
        System.out.println(name + " (" + staffId + ") clocked in for " + shift + " shift");
    }
    
    public void clockOut() {
        System.out.println(name + " (" + staffId + ") clocked out from " + shift + " shift");
    }
    
    public void accessIDCard() {
        System.out.println("ID Card access granted for " + name + " - " + getClass().getSimpleName());
    }
    
    public void processPayroll() {
        System.out.println("Processing payroll for " + name + ": $" + salary);
    }
    
    public void displayBasicInfo() {
        System.out.println("Staff ID: " + staffId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Shift: " + shift);
    }
    
    // Abstract method for specific duties
    public abstract void performDuties();
}

class Doctor extends MedicalStaff {
    private String specialization;
    private int patientsAssigned;
    
    public Doctor(String staffId, String name, String department, String shift, 
                  double salary, String specialization) {
        super(staffId, name, department, shift, salary);
        this.specialization = specialization;
        this.patientsAssigned = 0;
    }
    
    public void diagnosePatient(String patientName) {
        System.out.println("Dr. " + name + " diagnosing patient: " + patientName);
        patientsAssigned++;
    }
    
    public void prescribeMedicine(String patientName, String medicine) {
        System.out.println("Dr. " + name + " prescribed " + medicine + " to " + patientName);
    }
    
    public void performSurgery(String surgeryType) {
        System.out.println("Dr. " + name + " performing " + surgeryType + " surgery");
    }
    
    @Override
    public void performDuties() {
        System.out.println("=== DOCTOR DUTIES ===");
        displayBasicInfo();
        System.out.println("Specialization: " + specialization);
        System.out.println("Patients Assigned: " + patientsAssigned);
        System.out.println("Today's Tasks: Diagnose patients, prescribe medications, surgery consultations");
        System.out.println("====================\n");
    }
}

class Nurse extends MedicalStaff {
    private String nursingLevel;
    private int patientsMonitored;
    
    public Nurse(String staffId, String name, String department, String shift, 
                 double salary, String nursingLevel) {
        super(staffId, name, department, shift, salary);
        this.nursingLevel = nursingLevel;
        this.patientsMonitored = 0;
    }
    
    public void administerMedicine(String patientName, String medicine) {
        System.out.println("Nurse " + name + " administering " + medicine + " to " + patientName);
    }
    
    public void monitorPatient(String patientName) {
        System.out.println("Nurse " + name + " monitoring patient: " + patientName);
        patientsMonitored++;
    }
    
    public void assistProcedure(String procedureType) {
        System.out.println("Nurse " + name + " assisting in " + procedureType);
    }
    
    @Override
    public void performDuties() {
        System.out.println("=== NURSE DUTIES ===");
        displayBasicInfo();
        System.out.println("Nursing Level: " + nursingLevel);
        System.out.println("Patients Monitored: " + patientsMonitored);
        System.out.println("Today's Tasks: Patient care, medication administration, procedure assistance");
        System.out.println("===================\n");
    }
}

class Technician extends MedicalStaff {
    private String specializedEquipment;
    private int testsCompleted;
    
    public Technician(String staffId, String name, String department, String shift, 
                     double salary, String specializedEquipment) {
        super(staffId, name, department, shift, salary);
        this.specializedEquipment = specializedEquipment;
        this.testsCompleted = 0;
    }
    
    public void operateEquipment(String equipmentName) {
        System.out.println("Technician " + name + " operating " + equipmentName);
    }
    
    public void runTest(String testType, String patientName) {
        System.out.println("Technician " + name + " running " + testType + " for " + patientName);
        testsCompleted++;
    }
    
    public void maintainInstruments() {
        System.out.println("Technician " + name + " performing equipment maintenance");
    }
    
    @Override
    public void performDuties() {
        System.out.println("=== TECHNICIAN DUTIES ===");
        displayBasicInfo();
        System.out.println("Specialized Equipment: " + specializedEquipment);
        System.out.println("Tests Completed: " + testsCompleted);
        System.out.println("Today's Tasks: Equipment operation, test administration, maintenance");
        System.out.println("========================\n");
    }
}

class Administrator extends MedicalStaff {
    private String adminRole;
    private int appointmentsScheduled;
    
    public Administrator(String staffId, String name, String department, String shift, 
                        double salary, String adminRole) {
        super(staffId, name, department, shift, salary);
        this.adminRole = adminRole;
        this.appointmentsScheduled = 0;
    }
    
    public void scheduleAppointment(String patientName, String doctorName, String time) {
        System.out.println("Administrator " + name + " scheduled appointment:");
        System.out.println("Patient: " + patientName + " with Dr. " + doctorName + " at " + time);
        appointmentsScheduled++;
    }
    
    public void manageRecords(String patientName, String action) {
        System.out.println("Administrator " + name + " " + action + " records for " + patientName);
    }
    
    @Override
    public void performDuties() {
        System.out.println("=== ADMINISTRATOR DUTIES ===");
        displayBasicInfo();
        System.out.println("Admin Role: " + adminRole);
        System.out.println("Appointments Scheduled: " + appointmentsScheduled);
        System.out.println("Today's Tasks: Appointment scheduling, record management, coordination");
        System.out.println("============================\n");
    }
}

// Hospital Management System demonstrating Upcasting
class HospitalManagementSystem {
    private MedicalStaff[] staff;
    
    public HospitalManagementSystem(MedicalStaff[] staff) {
        this.staff = staff;
    }
    
    // Upcasting in action - treating all staff as MedicalStaff
    public void conductShiftScheduling() {
        System.out.println("=== SHIFT SCHEDULING FOR ALL STAFF ===");
        for (MedicalStaff member : staff) {
            member.clockIn(); // Common method available through upcasting
        }
        System.out.println("======================================\n");
    }
    
    public void processMonthlyPayroll() {
        System.out.println("=== MONTHLY PAYROLL PROCESSING ===");
        double totalPayroll = 0;
        for (MedicalStaff member : staff) {
            member.processPayroll(); // Common method through upcasting
            totalPayroll += member.salary;
        }
        System.out.println("Total Monthly Payroll: $" + totalPayroll);
        System.out.println("==================================\n");
    }
    
    public void grantIDCardAccess() {
        System.out.println("=== ID CARD ACCESS MANAGEMENT ===");
        for (MedicalStaff member : staff) {
            member.accessIDCard(); // Common method through upcasting
        }
        System.out.println("=================================\n");
    }
    
    public void displayAllStaffDuties() {
        System.out.println("=== DAILY DUTIES OVERVIEW ===");
        for (MedicalStaff member : staff) {
            member.performDuties(); // Polymorphic method call
        }
        System.out.println("=============================\n");
    }
}

public class HospitalDemo {
    public static void main(String[] args) {
        // Creating different types of medical staff
        MedicalStaff[] hospitalStaff = {
            new Doctor("DOC001", "Dr. Smith", "Cardiology", "Day", 15000, "Heart Surgery"),
            new Doctor("DOC002", "Dr. Johnson", "Neurology", "Night", 16000, "Brain Surgery"),
            new Nurse("NUR001", "Alice Brown", "ICU", "Day", 5000, "Senior"),
            new Nurse("NUR002", "Bob Wilson", "Emergency", "Night", 4800, "Junior"),
            new Technician("TECH001", "Carol Davis", "Radiology", "Day", 4000, "MRI Scanner"),
            new Technician("TECH002", "David Lee", "Laboratory", "Evening", 3800, "Blood Analyzer"),
            new Administrator("ADM001", "Eve Taylor", "Reception", "Day", 3500, "Appointment Coordinator"),
            new Administrator("ADM002", "Frank Miller", "Records", "Day", 3600, "Medical Records Manager")
        };
        
        // Demonstrate upcasting through hospital management system
        HospitalManagementSystem hospital = new HospitalManagementSystem(hospitalStaff);
        
        // All these methods work with MedicalStaff references (upcasting)
        hospital.conductShiftScheduling();
        hospital.grantIDCardAccess();
        hospital.processMonthlyPayroll();
        hospital.displayAllStaffDuties();
    }
}
