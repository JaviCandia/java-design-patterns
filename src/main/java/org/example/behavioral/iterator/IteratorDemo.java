package org.example.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

interface Iterator<T> {
    T next();

    boolean hasNext();

    T current();
}

// Element being iterated over
class Pokemon {
    public String name;
    public String type;

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

// Aggregate (Collection): knows how to provide an Iterator
class PokemonCollection {
    private List<Pokemon> pokemons = new ArrayList<>();

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public Pokemon getPokemonAt(int index) {
        if (index >= 0 && index < pokemons.size()) {
            return pokemons.get(index);
        }
        return null;
    }

    public int getLength() {
        return pokemons.size();
    }

    public PokemonIterator createIterator() {
        return new PokemonIterator(this);
    }
}

// Concrete Iterator: implements traversal logic
class PokemonIterator implements Iterator<Pokemon> {
    private PokemonCollection collection;
    private int position = 0;

    public PokemonIterator(PokemonCollection collection) {
        this.collection = collection;
    }

    @Override
    public Pokemon next() {
        if (this.hasNext()) {
            return this.collection.getPokemonAt(this.position++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.position < this.collection.getLength();
    }

    @Override
    public Pokemon current() {
        return this.collection.getPokemonAt(this.position);
    }
}

// Client: uses the Iterator without knowing internal structure of the collection
public class IteratorDemo {
    public static void main(String[] args) {
        PokemonCollection pokedex = new PokemonCollection();

        pokedex.addPokemon(new Pokemon("Pikachu", "Electric"));
        pokedex.addPokemon(new Pokemon("Charmander", "Fire"));
        pokedex.addPokemon(new Pokemon("Squirtle", "Water"));
        pokedex.addPokemon(new Pokemon("Bulbasaur", "Grass"));
        pokedex.addPokemon(new Pokemon("Jigglypuff", "Normal"));

        // Client only sees Iterator, not the underlying array
        PokemonIterator iterator = pokedex.createIterator();

        while (iterator.hasNext()) {
            Pokemon pokemon = iterator.next();
            if (pokemon != null) {
                System.out.printf("Pokemon: %s, Type: %s%n", pokemon.name, pokemon.type);
            }
        }
    }
}