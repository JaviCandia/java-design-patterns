package org.example.creational.factory;

import java.util.Scanner;

interface Hamburger {
    void prepare();
}

class ChickenHamburger implements Hamburger {
    @Override
    public void prepare() {
        System.out.println("Preparing a Chicken Hamburger");
    }
}

class MeatHamburger implements Hamburger {
    @Override
    public void prepare() {
        System.out.println("Preparing a Meat Hamburger");
    }
}

abstract class Restaurant {
    abstract Hamburger createHamburger();

    void orderHamburger() {
        final Hamburger hamburger = this.createHamburger();
        hamburger.prepare();
    }
}

class ChickenRestaurant extends Restaurant {
    @Override
    public Hamburger createHamburger() {
        return new ChickenHamburger();
    }
}

class MeatRestaurant extends Restaurant {
    @Override
    public Hamburger createHamburger() {
        return new MeatHamburger();
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        Restaurant restaurant;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choice your hamburger type: chicken/meat/bean");
        String burgerType = scanner.nextLine().toLowerCase();

        restaurant = switch (burgerType) {
            case "chicken" -> new ChickenRestaurant();
            case "meat" -> new MeatRestaurant();
            default -> throw new IllegalArgumentException("Invalid burger type");
        };

        restaurant.orderHamburger();
    }
}
