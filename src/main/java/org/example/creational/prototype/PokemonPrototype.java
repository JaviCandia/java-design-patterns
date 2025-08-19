package org.example.creational.prototype;

import java.util.ArrayList;
import java.util.List;

class Pokemon {
    String name;
    String type;
    int level;
    List<String> attacks;

    public Pokemon( String name, String type, int level, List<String> attacks) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.attacks = attacks;
    }

    public Pokemon(Pokemon pokemon) {
        this.name = pokemon.name;
        this.type = pokemon.type;
        this.level = pokemon.level;
        this.attacks = new ArrayList<>(pokemon.attacks);
    }
    
    public Pokemon clone(){
        return new Pokemon(this);
    }

    void displayInfo() {
        System.out.println("Name: " + this.name + "\n" +
                "Type: " + this.type + "\n" +
                "Level: " + this.level + "\n" +
                "Attacks: " + String.join(", ", this.attacks) + "\n");
    }
}

public class PokemonPrototype {
    public static void main(String[] args) {
        Pokemon pokemon1 = new Pokemon("Chicorita", "Herbal", 12, List.of("Latigazo", "Reggaeton"));
        pokemon1.displayInfo();

        Pokemon clone1 = pokemon1.clone();
        clone1.name = "Charmeleon";
        clone1.level = 16;
        clone1.attacks.add("Lanzallamas");

        pokemon1.displayInfo();
        clone1.displayInfo();
    }
}
