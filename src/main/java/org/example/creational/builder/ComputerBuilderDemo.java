package org.example.creational.builder;

public final class ComputerBuilderDemo {
    public static void main(String[] args) {
        Computer basicComputer = new ComputerBuilder()
                .setCPU("Intel Core 2 Duo")
                .setRAM("4GB")
                .setStorage("256GB")
                .build();

        System.out.println("Basic Computer: ");
        basicComputer.displayConfiguration();

        Computer gamingComputer = new ComputerBuilder()
                .setCPU("Intel i7 9th")
                .setRAM("16GB")
                .setStorage("1TB")
                .setGPU("RTX 5090")
                .build();

        System.out.println("\nGaming Computer: ");
        gamingComputer.displayConfiguration();
    }
}