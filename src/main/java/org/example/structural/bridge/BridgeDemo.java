package org.example.structural.bridge;

interface Ability {  void use(); }

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

class MagicSpell implements Ability {
    @Override
    public void use() {
        System.out.println("Casts a powerful magic spell");
    }
}

class FireballSpell implements Ability {
    @Override
    public void use() {
        System.out.println("Casts a fireball");
    }
}

abstract class Character {
    protected Ability ability;

    public Character(Ability ability) {
        this.ability = ability;
    }

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
        System.out.println("\nThe warrior is ready to fight");
        ability.use();
    }
}

class Mage extends Character {
    public Mage(Ability ability) {
        super(ability);
    }

    @Override
    public void performAbility() {
        System.out.println("\nThe mage prepares his magic");
        ability.use();
    }
}

class BridgeDemo {
    public static void main(String[] args) {
        Warrior warrior = new Warrior(new SwordAttack());
        warrior.performAbility();

        warrior.setAbility(new AxeAttack());
        warrior.performAbility();

        Mage mage = new Mage(new FireballSpell());
        mage.performAbility();
    }
}
