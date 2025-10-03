// File: GameBattle.java

public class GameBattle {

    // Basic attack
    public void attack(int damage) {
        System.out.println("Basic attack for " + damage + " points!");
    }

    // Attack with weapon
    public void attack(int damage, String weapon) {
        System.out.println("Attacking with " + weapon + " for " + damage + " points!");
    }

    // Critical hit with weapon
    public void attack(int damage, String weapon, boolean isCritical) {
        if (isCritical) {
            System.out.println("CRITICAL HIT! " + weapon + " deals " + (damage * 2) + " points!");
        } else {
            attack(damage, weapon);
        }
    }

    // Team attack
    public void attack(int damage, String[] teammates) {
        System.out.print("Team attack with ");
        for (int i = 0; i < teammates.length; i++) {
            System.out.print(teammates[i]);
            if (i < teammates.length - 1) System.out.print(", ");
        }
        System.out.println(" for " + (damage * teammates.length) + " total damage!");
    }

    public static void main(String[] args) {
        GameBattle gb = new GameBattle();
        gb.attack(50);
        gb.attack(75, "Sword");
        gb.attack(60, "Bow", true);
        gb.attack(40, new String[]{"Alice", "Bob"});
    }
}
