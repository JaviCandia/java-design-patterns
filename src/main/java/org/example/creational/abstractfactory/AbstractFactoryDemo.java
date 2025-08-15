package org.example.creational.abstractfactory;

interface Hamburger {
    void prepare();
}

interface Drink {
    void pour();
}

class ChickenHamburger implements Hamburger {
    @Override
    public void prepare() {
        System.out.println("Preparing Chicken Hamburger");
    }
}

class BeefHamburger implements Hamburger {
    @Override
    public void prepare() {
        System.out.println("Preparing Beef Hamburger");
    }
}

class Water implements Drink {
    @Override
    public void pour() {
        System.out.println("Pouring a glass of water");
    }
}

class Soda implements Drink {
    @Override
    public void pour() {
        System.out.println("Pouring a soda");
    }
}

interface RestaurantFactory{
    Hamburger createHamburger();
    Drink createDrink();
}

class FastfoodRestaurantFactory implements RestaurantFactory{

    @Override
    public Hamburger createHamburger() {
        return new BeefHamburger();
    }

    @Override
    public Drink createDrink() {
        return new Soda();
    }
}

class HealthyRestaurantFactory implements RestaurantFactory{
    @Override
    public Hamburger createHamburger() {
        return new ChickenHamburger();
    }

    @Override
    public Drink createDrink() {
        return new Water();
    }
}

public class AbstractFactoryDemo {

    public static void main(String[] args) {
        System.out.println("\nRegular menu order:");
        runOrder(new FastfoodRestaurantFactory());

        System.out.println("\nHealthy menu order:");
        runOrder(new HealthyRestaurantFactory());
    }

    static void runOrder(RestaurantFactory restaurantFactory) {
        Hamburger hamburger = restaurantFactory.createHamburger();
        Drink drink = restaurantFactory.createDrink();

        hamburger.prepare();
        drink.pour();
    }
}

