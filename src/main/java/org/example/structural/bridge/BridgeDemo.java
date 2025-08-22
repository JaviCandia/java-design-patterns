package org.example.structural.bridge;

// Bridge Pattern: Implementation hierarchy (abilities)
@FunctionalInterface
interface Ability {
    void use();
}

class SwordAttack implements Ability {
    @Override
    public void use() {
        System.out.println("Attacks fiercely with a sword");
    }
}

class AxeAttack implements Ability {
    @Override
    public void use() {
        System.out.println("Attacks brutally with an axe");
    }
}

class FireballSpell implements Ability {
    @Override
    public void use() {
        System.out.println("Casts a fireball");
    }
}

// Bridge Pattern: Abstraction hierarchy (characters)
// Holds a reference to Ability (the bridge to the implementation side)
abstract class Character {

    // Bridge: connects Character (abstraction) with Ability (implementation)
    protected Ability ability;

    public Character(Ability ability) {
        this.ability = ability;
    }

    // Bridge: allows changing the implementation at runtime
    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public abstract void performAbility();
}

class Warrior extends Character {
    public Warrior(Ability ability) {
        super(ability);
    }

    @Override
    public void performAbility() {
        System.out.println("The warrior is ready to fight");
        ability.use();
    }
}

class Mage extends Character {
    public Mage(Ability ability) {
        super(ability);
    }

    @Override
    public void performAbility() {
        System.out.println("The mage prepares his magic");
        ability.use();
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        // Use abstraction type so the abstract contract is "used" (IDE-friendly)
        Character warrior = new Warrior(new SwordAttack());
        warrior.performAbility();

        // Bridge in action: swap implementation at runtime
        warrior.setAbility(new AxeAttack());
        warrior.performAbility();

        Character mage = new Mage(new FireballSpell()); // now MagicSpell is used
        mage.performAbility();
    }
}