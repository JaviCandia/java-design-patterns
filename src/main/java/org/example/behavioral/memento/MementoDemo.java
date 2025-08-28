// MementoDemo.java
package org.example.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

// Memento: captures and holds the state of the Originator at a given time.
class GameCheckpoint {
    private final int level;
    private final int health;
    private final String location;

    public GameCheckpoint(int level, int health, String location) {
        this.level = level;
        this.health = health;
        this.location = location;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public String getLocation() {
        return location;
    }
}

// Originator: the object whose state changes; can save and restore via Mementos.
class Game {
    private int level = 1;
    private int health = 100;
    private String location = "Start";

    public Game() {
        System.out.printf(
                "Game started%n  level: %d%n  health: %d%n  location: %s%n%n",
                this.level, this.health, this.location
        );
    }

    public GameCheckpoint saveCheckpoint() {
        return new GameCheckpoint(this.level, this.health, this.location);
    }

    public void advanceTo(int level, int health, String location) {
        this.level = level;
        this.health = health;
        this.location = location;

        System.out.printf(
                "Advancing to...%n  level: %d%n  health: %d%n  location: %s%n%n",
                this.level, this.health, this.location
        );
    }

    public void restoreFrom(GameCheckpoint checkpoint) {
        this.level = checkpoint.getLevel();
        this.health = checkpoint.getHealth();
        this.location = checkpoint.getLocation();

        System.out.printf(
                "Checkpoint loaded%n  level: %d%n  health: %d%n  location: %s%n%n",
                this.level, this.health, this.location
        );
    }
}

// Caretaker: manages Mementos, deciding when to save or restore states.
class CheckpointHistory {
    private final List<GameCheckpoint> stack = new ArrayList<>();

    public void push(GameCheckpoint checkpoint) {
        this.stack.add(checkpoint);
    }

    public GameCheckpoint pop() {
        return this.stack.isEmpty() ? null : this.stack.removeLast();
    }
}

public class MementoDemo {
    public static void main(String[] args) {
        Game game = new Game();
        CheckpointHistory history = new CheckpointHistory();

        history.push(game.saveCheckpoint());

        // Player progresses in the game
        game.advanceTo(2, 90, "Enchanted Forest");
        history.push(game.saveCheckpoint());

        game.advanceTo(3, 70, "Dark Cave");
        history.push(game.saveCheckpoint());

        game.advanceTo(4, 50, "Dragon's Castle");

        System.out.println("The player dies!");
        System.out.println("Restoring last checkpoint...\n");
        game.restoreFrom(history.pop());
    }
}
