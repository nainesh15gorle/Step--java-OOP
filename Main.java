import java.time.LocalDate;
import java.util.*;

// ---- Immutable MedicalRecord ----
final class MedicalRecord {
    private final String id, dna, bloodType;
    private final String[] allergies, history;
    private final LocalDate birthDate;

    MedicalRecord(String id, String dna, String[] allergies, String[] history, LocalDate birth, String blood) {
        if (id == null || dna == null || birth == null) throw new IllegalArgumentException("Bad data");
        this.id = id; this.dna = dna; this.allergies = copy(allergies); this.history = copy(history);
        this.birthDate = birth; this.bloodType = blood;
    }
    private static String[] copy(String[] a) { return a == null ? new String[0] : Arrays.copyOf(a, a.length); }
    public String[] getAllergies() { return copy(allergies); }
    public String[] getHistory() { return copy(history); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }
    public final boolean isAllergicTo(String s) { return Arrays.stream(allergies).anyMatch(a -> a.equalsIgnoreCase(s)); }
}

// ---- Patient ----
class Patient {
    private final String id; private final MedicalRecord record;
    private String name, contact, insurance, physician; private int room;
    private final List<String> audit = new ArrayList<>();

    // Emergency
    Patient(String name, String contact) {
        this("TMP-" + UUID.randomUUID(), name, contact, "N/A", 0, "N/A",
             new MedicalRecord("MR-" + UUID.randomUUID(), "UNK", null, null, LocalDate.now().minusYears(30), "O+"));
        audit.add("Emergency admission");
    }
    // Standard
    Patient(String id, String name, String contact, String ins, int room, String doc,
            MedicalRecord record) {
        this.id = id; this.name = name; this.contact = contact; this.insurance = ins;
        this.room = room; this.physician = doc; this.record = record;
        audit.add("Standard/Transfer admission");
    }
    String getBasicInfo() { return id + "|" + name + "|" + record.getBirthDate() + "|" + record.getBloodType(); }
    public String getPublicInfo() { return "Name:" + name + "|Room:" + room; }
    public void setName(String n) { name = n; audit.add("Name updated"); }
    public void setRoom(int r) { room = r; audit.add("Room moved"); }
    public MedicalRecord getRecord() { return record; }
    public String getId() { return id; }
    public String toString() { return "Patient[" + id + "] " + audit; }
}

// ---- Staff ----
abstract class Staff { String name; Staff(String n){name=n;} abstract boolean canAccess(Patient p); }
class Doctor extends Staff {
    Doctor(String n){super(n);} boolean canAccess(Patient p){return true;} // doctors see all
    public String toString(){return "Dr."+name;}
}
class Nurse extends Staff {
    Nurse(String n){super(n);} boolean canAccess(Patient p){return true;} // simplified
    public String toString(){return "Nurse "+name;}
}
class Administrator extends Staff {
    Set<String> perms; Administrator(String n, Set<String> p){super(n); perms=p;}
    boolean canAccess(Patient p){return perms.contains("VIEW_BASIC");}
    public String toString(){return "Admin "+name;}
}

// ---- HospitalSystem ----
class HospitalSystem {
    private final Map<String, Patient> registry = new HashMap<>();
    public boolean admitPatient(Patient p, Staff s) {
        if (s.canAccess(p)) { registry.put(p.getId(), p); return true; }
        return false;
    }
    public void transfer(String id, int newRoom) { if (registry.containsKey(id)) registry.get(id).setRoom(newRoom); }
    public Optional<Patient> find(String id){ return Optional.ofNullable(registry.get(id)); }
}

// ---- Demo ----
public class Main {
    public static void main(String[] args) {
        MedicalRecord mr = new MedicalRecord("R1","DNA123",new String[]{"Peanuts"},null,LocalDate.of(1990,1,1),"A+");
        Patient p1 = new Patient("P1","Alice","123","InsureCo",101,"Dr.Smith",mr);
        Staff doc = new Doctor("John");
        HospitalSystem hs = new HospitalSystem();
        System.out.println(hs.admitPatient(p1, doc)); // true
        System.out.println(p1.getPublicInfo());       // Name:Alice|Room:101
    }
}
