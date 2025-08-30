package org.example.behavioral.templatemethod;

abstract class HotBeverage {

    // Template Method
    public final void prepare() {
        boilWater();
        addMainIngredient();
        pourInCup();
        addExtras();
    }

    private void boilWater() {
        System.out.println("Boiling water...");
    }

    private void pourInCup() {
        System.out.println("Pouring into the cup...");
    }

    // Primitive Operations
    protected abstract void addMainIngredient();
    protected abstract void addExtras();
}

// ConcreteClass
class Tea extends HotBeverage {
    @Override
    protected void addMainIngredient() {
        System.out.println("Adding a tea bag");
    }

    @Override
    protected void addExtras() {
        System.out.println("Adding honey and lemon");
    }
}

// ConcreteClass
class Coffee extends HotBeverage {
    @Override
    protected void addMainIngredient() {
        System.out.println("Adding ground coffee");
    }

    @Override
    protected void addExtras() {
        System.out.println("Adding sugar and milk");
    }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        System.out.println("Preparing Tea");
        HotBeverage tea = new Tea();
        tea.prepare();

        System.out.println("\nPreparing Coffee");
        HotBeverage coffee = new Coffee();
        coffee.prepare();
    }
}
