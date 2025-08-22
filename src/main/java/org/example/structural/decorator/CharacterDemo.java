package org.example.structural.decorator;

interface Character {
    String getDescription();

    Stats getStats();
}

final class Stats {
    public int attack;
    public int defense;

    public Stats(int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "{ attack: " + attack + ", defense: " + defense + " }";
    }
}

class BasicCharacter implements Character {
    @Override
    public String getDescription() {
        return "Basic Charecter";
    }

    @Override
    public Stats getStats() {
        return new Stats(10, 10);
    }
}


abstract class CharacterDecorator implements Character {
    protected Character character;

    protected CharacterDecorator(Character character) {
        this.character = character;
    }

    @Override
    public String getDescription() {
        return this.character.getDescription();
    }

    @Override
    public Stats getStats() {
        return this.character.getStats();
    }
}

class HelmetDecorator extends CharacterDecorator {

    public HelmetDecorator(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return this.character.getDescription() + "\n * with Helmet";
    }

    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.attack, stats.defense + 5);
    }
}

class ShieldDecorator extends CharacterDecorator {

    public ShieldDecorator(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return this.character.getDescription() + "\n * with Shield";
    }

    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.attack, stats.defense + 10);
    }
}

class SwordDecorator extends CharacterDecorator {

    public SwordDecorator(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return this.character.getDescription() + "\n * with Sword";
    }

    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.attack + 7, stats.defense);
    }
}

class RingDecorator extends CharacterDecorator {

    public RingDecorator(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return this.character.getDescription() + "\n * with Ring";
    }

    @Override
    public Stats getStats() {
        Stats stats = this.character.getStats();
        return new Stats(stats.attack + 3, stats.defense);
    }
}

class CharacterDemo {
    public static void main(String[] args) {
        // Crear un personaje básico
        Character character = new BasicCharacter();
        System.out.println("\nPersonaje inicial: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        // Añadir un casco al personaje
        character = new HelmetDecorator(character);
        System.out.println("\nCon Casco: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        // Añadir un escudo al personaje
        character = new ShieldDecorator(character);
        System.out.println("\nCon Escudo: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        // Añadir una espada al personaje
        character = new SwordDecorator(character);
        System.out.println("\nCon Espada: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        character = new RingDecorator(character);
        System.out.println("\nCon Anillo: " + character.getDescription());
        System.out.println("Estadísticas: " + character.getStats());

        System.out.println("\n\n");
    }
}