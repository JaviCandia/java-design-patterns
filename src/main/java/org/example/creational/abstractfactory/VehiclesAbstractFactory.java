package org.example.creational.abstractfactory;

interface Vehicle {
    void assemble();
}

interface Engine {
    void start();
}

class ElectricCar implements Vehicle {
    @Override
    public void assemble() {
        System.out.println("Assembling an electric car");
    }
}

class GasCar implements Vehicle {
    @Override
    public void assemble() {
        System.out.println("Assembling a gas car");
    }
}

class ElectricEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Starting an electric engine");
    }
}

class GasEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Starting a gas engine");
    }
}

interface VehicleFactory {
    Vehicle createVehicle();
    Engine createEngine();
}

class ElectricVehicleFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new ElectricCar();
    }

    @Override
    public Engine createEngine() {
        return new ElectricEngine();
    }
}

class GasVehicleFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new GasCar();
    }

    @Override
    public Engine createEngine() {
        return new GasEngine();
    }
}

public class VehiclesAbstractFactory {
    public static void main(String[] args) {
        System.out.println("Creating electric vehicle:");
        createVehicle(new ElectricVehicleFactory());

        System.out.println("\nCreating gas-powered vehicle:");
        createVehicle(new GasVehicleFactory());
    }

    static void createVehicle(VehicleFactory factory) {
        Vehicle vehicle = factory.createVehicle();
        Engine engine = factory.createEngine();

        vehicle.assemble();
        engine.start();
    }
}