package org.example.behavioral.strategy;

interface MovementStrategy {
    void move();
}

// Concrete Strategy
class SwimFast implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("The duck swims quickly across the water.\n");
    }
}

// Concrete Strategy
class FlyOverWater implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("The duck flies gracefully over the water.\n");
    }
}

// Concrete Strategy
class WalkClumsily implements MovementStrategy {
    @Override
    public void move() {
        System.out.println("The duck walks clumsily along the shore.\n");
    }
}

// Context
class Duck {
    private final String name;
    private MovementStrategy movementStrategy;

    public Duck(String name, MovementStrategy strategy) {
        this.name = name;
        this.movementStrategy = strategy;
        System.out.printf("%s ready to race%n", name);
    }

    public void performMove() {
        System.out.printf("%s is getting ready to move...%n", name);
        movementStrategy.move();
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
        System.out.printf("%s changed strategy.%n", name);
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        Duck duck1 = new Duck("Speedy Duck", new SwimFast());
        Duck duck2 = new Duck("Flying Duck", new FlyOverWater());
        Duck duck3 = new Duck("Clumsy Duck", new WalkClumsily());

        System.out.println("The duck race begins!\n");
        duck1.performMove();
        duck2.performMove();
        duck3.performMove();

        duck3.setMovementStrategy(new FlyOverWater());
        duck3.performMove();

        duck3.setMovementStrategy(new SwimFast());
        duck3.performMove();
    }
}

