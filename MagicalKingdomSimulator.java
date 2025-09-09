import java.util.*;

// === Abstract Base Class ===
abstract class MagicalStructure {
    String structureName;
    int magicPower;
    String location;
    boolean isActive;

    // Constructor Overloading with this()
    public MagicalStructure(String structureName) {
        this(structureName, 50, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    // Abstract method
    abstract void castMagicSpell();
}

// === WizardTower ===
class WizardTower extends MagicalStructure {
    int spellCapacity;
    String[] knownSpells;

    public WizardTower(String name) {
        this(name, 100, "High Hill", true, 3, new String[]{"Fireball", "Shield"});
    }

    public WizardTower(String name, int spellCapacity, String[] spells) {
        this(name, 120, "Mystic Peak", true, spellCapacity, spells);
    }

    public WizardTower(String name, int magicPower, String location, boolean isActive, int spellCapacity, String[] spells) {
        super(name, magicPower, location, isActive);
        this.spellCapacity = spellCapacity;
        this.knownSpells = spells;
    }

    @Override
    void castMagicSpell() {
        System.out.println(structureName + " casts " + (knownSpells.length > 0 ? knownSpells[0] : "a random spell") + "!");
    }
}

// === EnchantedCastle ===
class EnchantedCastle extends MagicalStructure {
    int defenseRating;
    boolean hasDrawbridge;

    public EnchantedCastle(String name) {
        this(name, 80, "Valley", true, 100, true);
    }

    public EnchantedCastle(String name, int defenseRating) {
        this(name, 100, "Royal Grounds", true, defenseRating, true);
    }

    public EnchantedCastle(String name, int magicPower, String location, boolean isActive, int defenseRating, boolean hasDrawbridge) {
        super(name, magicPower, location, isActive);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    @Override
    void castMagicSpell() {
        System.out.println(structureName + " radiates protective magic with defense rating " + defenseRating);
    }
}

// === MysticLibrary ===
class MysticLibrary extends MagicalStructure {
    int bookCount;
    String ancientLanguage;

    public MysticLibrary(String name) {
        this(name, 70, "Arcane City", true, 500, "Elder Runes");
    }

    public MysticLibrary(String name, int bookCount, String lang) {
        this(name, 90, "Ancient Grove", true, bookCount, lang);
    }

    public MysticLibrary(String name, int magicPower, String location, boolean isActive, int bookCount, String lang) {
        super(name, magicPower, location, isActive);
        this.bookCount = bookCount;
        this.ancientLanguage = lang;
    }

    @Override
    void castMagicSpell() {
        System.out.println(structureName + " whispers ancient knowledge in " + ancientLanguage);
    }
}

// === DragonLair ===
class DragonLair extends MagicalStructure {
    String dragonType;
    int treasureValue;

    public DragonLair(String name) {
        this(name, 150, "Cave of Flames", true, "Fire Dragon", 1000);
    }

    public DragonLair(String name, String dragonType, int treasureValue) {
        this(name, 180, "Hidden Mountain", true, dragonType, treasureValue);
    }

    public DragonLair(String name, int magicPower, String location, boolean isActive, String dragonType, int treasureValue) {
        super(name, magicPower, location, isActive);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    @Override
    void castMagicSpell() {
        System.out.println(structureName + " unleashes " + dragonType + "'s fury! 🔥");
    }
}

// === Interactions ===
class MagicInteractions {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return s1.isActive && s2.isActive;
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (!canStructuresInteract(attacker, defender)) return "One structure is inactive!";
        return attacker.magicPower > defender.magicPower
                ? attacker.structureName + " defeats " + defender.structureName
                : defender.structureName + " resists " + attacker.structureName;
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        int towerCount = 0;
        for (MagicalStructure s : structures) {
            total += s.magicPower;
            if (s instanceof WizardTower) towerCount++;
        }
        // Special effect: Multiple towers form network
        if (towerCount > 1) total += 200;
        return total;
    }

    // Special Effects
    public static void applySpecialEffects(MagicalStructure s1, MagicalStructure s2) {
        if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) {
            ((WizardTower) s1).spellCapacity *= 2;
            System.out.println("✨ Knowledge boost! " + s1.structureName + " doubles spell capacity.");
        }
        if (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) {
            ((EnchantedCastle) s1).defenseRating *= 3;
            System.out.println("🛡 Dragon guard! " + s1.structureName + " triples defense.");
        }
    }
}

// === Kingdom Manager ===
class KingdomManager {
    public static void categorize(MagicalStructure s) {
        if (s instanceof WizardTower) System.out.println(s.structureName + " belongs to Magic category.");
        else if (s instanceof EnchantedCastle) System.out.println(s.structureName + " belongs to Defense category.");
        else if (s instanceof MysticLibrary) System.out.println(s.structureName + " belongs to Knowledge category.");
        else if (s instanceof DragonLair) System.out.println(s.structureName + " belongs to Treasure category.");
    }

    public static double calculateTax(MagicalStructure s) {
        if (s instanceof WizardTower) return s.magicPower * 0.1;
        if (s instanceof EnchantedCastle) return s.magicPower * 0.2;
        if (s instanceof MysticLibrary) return s.magicPower * 0.05;
        if (s instanceof DragonLair) return s.magicPower * 0.3;
        return 0;
    }

    public static String determineSpecialization(MagicalStructure[] structures) {
        int magic = 0, defense = 0, treasure = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower || s instanceof MysticLibrary) magic += s.magicPower;
            if (s instanceof EnchantedCastle) defense += s.magicPower;
            if (s instanceof DragonLair) treasure += s.magicPower;
        }
        if (magic >= defense && magic >= treasure) return "Magic-focused Kingdom";
        if (defense >= magic && defense >= treasure) return "Defense-focused Kingdom";
        return "Treasure-focused Kingdom";
    }
}

// === Main Simulation ===
public class MagicalKingdomSimulator {
    public static void main(String[] args) {
        MagicalStructure[] kingdom = {
            new WizardTower("Arcane Tower"),
            new EnchantedCastle("Royal Fortress"),
            new MysticLibrary("Ancient Library"),
            new DragonLair("Smaug's Lair", "Shadow Dragon", 2000)
        };

        System.out.println("🏰 Welcome to the Magical Kingdom!");

        // Categorize
        for (MagicalStructure s : kingdom) {
            KingdomManager.categorize(s);
            s.castMagicSpell();
        }

        // Apply interactions
        MagicInteractions.applySpecialEffects(kingdom[0], kingdom[2]); // Tower + Library
        MagicInteractions.applySpecialEffects(kingdom[1], kingdom[3]); // Castle + Lair

        // Battles
        System.out.println(MagicInteractions.performMagicBattle(kingdom[0], kingdom[3]));

        // Tax
        for (MagicalStructure s : kingdom) {
            System.out.println(s.structureName + " tax: " + KingdomManager.calculateTax(s));
        }

        // Kingdom Power
        System.out.println("⚡ Total Magic Power: " + MagicInteractions.calculateKingdomMagicPower(kingdom));

        // Specialization
        System.out.println("👑 Kingdom Specialization: " + KingdomManager.determineSpecialization(kingdom));
    }
}
