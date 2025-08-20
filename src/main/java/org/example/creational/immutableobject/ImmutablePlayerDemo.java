package org.example.creational.immutableobject;

import java.util.Objects;

class Player {
    public final String name;
    public final int score;
    public final int level;

    public Player(String name, int score, int level) {
        this.name = name;
        this.score = score;
        this.level = level;
    }

    public Player copyWith(String name, Integer score, Integer level) {
        return new Player(
                Objects.requireNonNullElse(name, this.name),
                Objects.requireNonNullElse(score, this.score),
                Objects.requireNonNullElse(level, this.level)
        );
    }

    public void displayState() {
        System.out.printf("""
                Player: %s
                Score: %d
                Level: %d
                """, this.name, this.score, this.level);
    }
}

public class ImmutablePlayerDemo {
    public static void main(String[] args) {
        System.out.println("Initial State:");
        Player player = new Player("Carlos", 0, 1);
        player.displayState();

        System.out.println("\nAfter scoring points:");
        player = player.copyWith(null, 10, null);
        player.displayState();

        System.out.println("\nAfter leveling up:");
        player = player.copyWith(null, null, 2);
        player.displayState();

        System.out.println("\nAfter changing name:");
        player = player.copyWith("Carlos Pro", null, null);
        player.displayState();
    }
}
