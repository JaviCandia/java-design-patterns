package org.example.creational.factorymethod;

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

class BeefHamburger implements Hamburger {
    @Override
    public void prepare() {
        System.out.println("Preparing a Beef Hamburger");
    }
}

class BeanHamburger implements  Hamburger {
    @Override
    public void prepare() {
        System.out.println("Preparing a Bean Hamburger");
    }
}

abstract class Restaurant {
    protected abstract Hamburger createHamburger();

    void orderHamburger() {
        final Hamburger hamburger = this.createHamburger();
        hamburger.prepare();
    }
}

class ChickenRestaurant extends Restaurant {
    @Override
    protected Hamburger createHamburger() {
        return new ChickenHamburger();
    }
}

class BeefRestaurant extends Restaurant {
    @Override
    protected Hamburger createHamburger() {
        return new BeefHamburger();
    }
}

class BeanRestaurant extends Restaurant {
    @Override
    protected Hamburger createHamburger () {
        return new BeanHamburger();
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {
        Restaurant restaurant;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choice your hamburger type: chicken | beef | bean");
        String burgerType = scanner.nextLine().toLowerCase();

        restaurant = switch (burgerType) {
            case "chicken" -> new ChickenRestaurant();
            case "beef" -> new BeefRestaurant();
            case "bean" -> new BeanRestaurant();
            default -> throw new IllegalArgumentException("Invalid burger type");
        };

        restaurant.orderHamburger();
    }
}
