public class Animal {
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;

    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void sleep() {
        System.out.println("Animal is sleeping");
    }

    public void move() {
        System.out.println("Animal is moving");
    }

    public String getAnimalInfo() {
        return species + ", " + habitat + ", LifeSpan: " + lifespan + " yrs";
    }
}

class Mammal extends Animal {
    protected String furColor;
    protected boolean hasWarmBlood;
    protected int gestationPeriod;

    public Mammal(String species, String habitat, int lifespan, boolean isWildlife,
                  String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife);
        this.furColor = furColor;
        this.gestationPeriod = gestationPeriod;
        this.hasWarmBlood = true;
        System.out.println("Mammal constructor: Adding mammal traits");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Mammal is walking/running");
    }

    public void nurse() {
        System.out.println("Mammal is nursing offspring");
    }

    public void regulateTemperature() {
        System.out.println("Maintaining body temperature");
    }
}

class Dog extends Mammal {
    String breed;
    boolean isDomesticated;
    int loyaltyLevel;
    String favoriteActivity;

    // Basic constructor
    public Dog() {
        super("Dog", "Domestic", 13, false, "Brown", 60);
        this.breed = "Mixed";
        this.isDomesticated = true;
        this.loyaltyLevel = 8;
        this.favoriteActivity = "Playing fetch";
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    // Detailed constructor
    public Dog(String breed, boolean isDomesticated, int loyaltyLevel, String favoriteActivity,
               String species, String habitat, int lifespan, boolean isWildlife, String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    // Copy constructor
    public Dog(Dog source) {
        this(source.breed, source.isDomesticated, source.loyaltyLevel, source.favoriteActivity,
             source.species, source.habitat, source.lifespan, source.isWildlife, source.furColor, source.gestationPeriod);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Wagging tail while eating");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Loyalty level: " + loyaltyLevel);
    }

    public void demonstrateInheritance() {
        eat(); move(); sleep(); nurse(); regulateTemperature();
        bark(); fetch(); showLoyalty();
    }

    public static void main(String[] args) {
        Dog d1 = new Dog();
        d1.demonstrateInheritance();

        Dog d2 = new Dog("Labrador", true, 10, "Swimming", "Dog", "Home", 13, false, "Yellow", 63);
        d2.demonstrateInheritance();

        Dog d3 = new Dog(d2);
        d3.demonstrateInheritance();
    }
}
