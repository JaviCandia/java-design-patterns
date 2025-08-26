package org.example.behavioral.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface Command {
    void execute();
}

// Receivers (the objects that perform the real work)
class Light {
    void turnOn() {
        System.out.println("The light is on");
    }

    void turnOff() {
        System.out.println("The light is off");
    }
}

class Fan {
    void on() {
        System.out.println("The fan is on");
    }

    void off() {
        System.out.println("The fan is off");
    }
}

// Concrete Commands (each encapsulates an action over a Receiver)
class LightOnCommand implements Command {
    private final Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.turnOn();
    }
}

class LightOffCommand implements Command {
    private final Light light;

    LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.turnOff();
    }
}

class FanOnCommand implements Command {
    private final Fan fan;

    FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        this.fan.on();
    }
}

class FanOffCommand implements Command {
    private final Fan fan;

    FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        this.fan.off();
    }
}

// Invoker (stores and triggers Commands)
class RemoteControl {
    private final Map<String, Command> commands = new HashMap<>();

    void setCommand(String button, Command command) {
        this.commands.put(button, command);
    }

    void pressButton(String button) {
        Command command = this.commands.get(button);
        if (command != null) {
            command.execute();
            return;
        }
        System.out.println("No command has been assigned to that button");
    }
}

// Client (wires Invoker, Commands, and Receivers)
public class CommandDemo {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        Light light = new Light();
        Fan fan = new Fan();

        // Create commands for the devices
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        Command fanOnCommand = new FanOnCommand(fan);
        Command fanOffCommand = new FanOffCommand(fan);

        // Assign actions to the remote control
        remoteControl.setCommand("1", lightOnCommand);
        remoteControl.setCommand("2", lightOffCommand);
        remoteControl.setCommand("3", fanOnCommand);
        remoteControl.setCommand("4", fanOffCommand);

        boolean continueProgram = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nPress a button on the remote:");
            System.out.println("  1. Turn on light");
            System.out.println("  2. Turn off light");
            System.out.println("  3. Turn on fan");
            System.out.println("  4. Turn off fan");
            System.out.print("\nButton: ");

            String pressedButton = scanner.nextLine();
            remoteControl.pressButton(pressedButton);

            System.out.print("\nDo you want to continue? (y/n): ");
            String continueProgramResponse = scanner.nextLine().toLowerCase();
            continueProgram = !continueProgramResponse.equals("n");
        } while (continueProgram);

        scanner.close();
    }
}
