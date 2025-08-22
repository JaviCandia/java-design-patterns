package org.example.structural.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface MenuComponent {
    void showDetails(String indent);
}

class MenuItem implements MenuComponent {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showDetails(String indent) {
        System.out.printf("%s - %s: $%.2f\n",  indent, name, price);
    }
}

class MenuCategory implements MenuComponent {
    private final String name;
    private final List<MenuComponent> items;

    public MenuCategory(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void add(MenuComponent... items) {
        Collections.addAll(this.items, items);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "> " + this.name);

        this.items.forEach(item -> item.showDetails(indent + "   "));
    }
}

public class CompositeMenu {
    public static void main(String[] args) {
        MenuItem salad = new MenuItem("Salad", 5.99);
        MenuItem soup = new MenuItem("Soup", 4.99);
        MenuItem steak = new MenuItem("Steak", 15.99);
        MenuItem soda = new MenuItem("Soda", 2.50);
        MenuItem chocolateCake = new MenuItem("Chocolate Cake", 6.50);
        MenuItem coffee = new MenuItem("Coffee", 1.99);

        MenuCategory appetizers = new MenuCategory("Appetizers");
        appetizers.add(salad);
        appetizers.add(soup);

        MenuCategory mainCourse = new MenuCategory("Main Course");
        mainCourse.add(steak);

        MenuCategory beverages = new MenuCategory("Beverages");
        beverages.add(soda);
        beverages.add(coffee);

        MenuCategory desserts = new MenuCategory("Desserts");
        desserts.add(chocolateCake);

        MenuCategory mainMenu = new MenuCategory("Main Menu");
        mainMenu.add(appetizers, mainCourse, beverages, desserts);

        System.out.println("Restaurant Menu:");
        mainMenu.showDetails("");
    }
}