import java.util.*;

// VirtualPet Class
class VirtualPet {
    // Fields
    private final String petId; // unique ID
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private String currentStage;
    private boolean isGhost = false;

    // Static fields
    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    static int totalPetsCreated = 0;

    // === Constructors ===
    // Default constructor -> mysterious egg with random species
    public VirtualPet() {
        this("Unknown", getRandomSpecies(), 0, 50, 50, EVOLUTION_STAGES[0]); // Egg
    }

    // Constructor with only name -> Baby stage
    public VirtualPet(String petName) {
        this(petName, getRandomSpecies(), 1, 60, 60, EVOLUTION_STAGES[1]); // Baby
    }

    // Constructor with name + species -> Child stage
    public VirtualPet(String petName, String species) {
        this(petName, species, 3, 70, 70, EVOLUTION_STAGES[2]); // Child
    }

    // Full constructor -> ultimate control
    public VirtualPet(String petName, String species, int age, int happiness, int health, String stage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.currentStage = stage;
        totalPetsCreated++;
    }

    // === Static Method to generate ID ===
    public static String generatePetId() {
        return "PET-" + UUID.randomUUID().toString().substring(0, 5);
    }

    // === Care Methods ===
    public void feedPet() {
        if (isGhost) return;
        health += 10;
        happiness += 5;
        capStats();
    }

    public void playWithPet() {
        if (isGhost) return;
        happiness += 15;
        health -= 5; // gets tired
        capStats();
    }

    public void healPet() {
        if (isGhost) return;
        health += 20;
        capStats();
    }

    // === Simulation Methods ===
    public void simulateDay() {
        if (isGhost) return;

        age++;
        health -= (int)(Math.random() * 15);   // random decrease
        happiness -= (int)(Math.random() * 10);

        if (health <= 0) {
            becomeGhost();
        } else {
            evolvePet();
        }
        capStats();
    }

    private void evolvePet() {
        int stageIndex = Math.min(age / 3, EVOLUTION_STAGES.length - 1); // evolve every 3 days
        currentStage = EVOLUTION_STAGES[stageIndex];
    }

    private void becomeGhost() {
        isGhost = true;
        currentStage = "Ghost";
        happiness = 0;
        health = 0;
    }

    public String getPetStatus() {
        return petName + " (" + species + ") [" + petId + "] → " +
                "Stage: " + currentStage + ", Age: " + age +
                ", Happiness: " + happiness + ", Health: " + health +
                (isGhost ? " 👻 (Haunting...)" : "");
    }

    // Utility
    private void capStats() {
        happiness = Math.min(100, Math.max(0, happiness));
        health = Math.min(100, Math.max(0, health));
    }

    private static String getRandomSpecies() {
        String[] speciesList = {"Dragon", "Cat", "Dog", "Phoenix", "Alien"};
        return speciesList[new Random().nextInt(speciesList.length)];
    }
}

// === Main Simulation ===
public class VirtualPetSimulator {
    public static void main(String[] args) {
        // Pet Daycare
        List<VirtualPet> daycare = new ArrayList<>();

        // Create pets using different constructors
        daycare.add(new VirtualPet());  // Egg
        daycare.add(new VirtualPet("Fluffy")); // Baby
        daycare.add(new VirtualPet("Rex", "Wolf")); // Child
        daycare.add(new VirtualPet("Zara", "Fox", 5, 80, 90, VirtualPet.EVOLUTION_STAGES[3])); // Custom Teen

        System.out.println("🐾 Welcome to the Pet Daycare! Total pets: " + VirtualPet.totalPetsCreated);

        // Simulate 7 days
        for (int day = 1; day <= 7; day++) {
            System.out.println("\n📅 Day " + day + ":");
            for (VirtualPet pet : daycare) {
                pet.simulateDay();
                System.out.println(pet.getPetStatus());
            }
        }
    }
}
