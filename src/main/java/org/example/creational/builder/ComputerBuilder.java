package org.example.creational.builder;

public class ComputerBuilder {
    private final Computer computer;

    public ComputerBuilder() {
        this.computer = new Computer();
    }

    ComputerBuilder setCPU(String cpu) {
        this.computer.cpu = cpu;
        return this;
    }

    ComputerBuilder setRAM(String ram) {
        this.computer.ram = ram;
        return this;
    }

    ComputerBuilder setStorage(String storage) {
        this.computer.storage = storage;
        return this;
    }

    ComputerBuilder setGPU(String gpu) {
        this.computer.gpu = gpu;
        return this;
    }

    public Computer build() {
        return this.computer;
    }
}