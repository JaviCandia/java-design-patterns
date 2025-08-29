package org.example.behavioral.state;

import java.util.Scanner;

interface State {
    String getName();

    void insertMoney();

    void selectProduct();

    void dispenseProduct();
}

// Context
class VendingMachine {
    private State state;

    public VendingMachine() {
        this.state = new WaitingForMoney(this);
    }

    public void insertMoney() {
        state.insertMoney();
    }

    public void selectProduct() {
        state.selectProduct();
    }

    public void dispenseProduct() {
        state.dispenseProduct();
    }

    public void setState(State newState) {
        this.state = newState;
        System.out.println("State changed to: " + newState.getName());
    }

    public String getStateName() {
        return state.getName();
    }
}

// ConcreteState
class WaitingForMoney implements State {
    private final VendingMachine vendingMachine;

    public WaitingForMoney(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public String getName() {
        return "Waiting for Money";
    }

    @Override
    public void insertMoney() {
        System.out.println("Money inserted: You can now select a product.");
        vendingMachine.setState(new ProductSelected(vendingMachine));
    }

    @Override
    public void selectProduct() {
        System.out.println("Please insert money first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please insert money first.");
    }
}

// ConcreteState
class ProductSelected implements State {
    private final VendingMachine vendingMachine;

    public ProductSelected(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public String getName() {
        return "Selecting Product";
    }

    @Override
    public void insertMoney() {
        System.out.println("Please select a product — money already inserted.");
    }

    @Override
    public void selectProduct() {
        vendingMachine.setState(new DispensingProduct(vendingMachine));
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product before dispensing.");
    }
}

// ConcreteState
class DispensingProduct implements State {
    private final VendingMachine vendingMachine;

    public DispensingProduct(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public String getName() {
        return "Dispensing Product";
    }

    @Override
    public void insertMoney() {
        System.out.println("Please wait until the product is delivered.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product already selected and being dispensed.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product dispensed. Changing state to Waiting for Money.");
        vendingMachine.setState(new WaitingForMoney(vendingMachine));
    }
}

public class StateDemo {
    public static void main(String[] args) throws InterruptedException {
        VendingMachine vendingMachine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        String selectedOption;

        do {
            System.out.printf(
                    """
                            
                            Select an option (Current state: %s)
                              1. Insert money
                              2. Select product
                              3. Dispense product
                              4. Exit
                            Option:\s""",
                    vendingMachine.getStateName()
            );

            selectedOption = scanner.nextLine().trim();

            switch (selectedOption) {
                case "1":
                    vendingMachine.insertMoney();
                    break;
                case "2":
                    vendingMachine.selectProduct();
                    break;
                case "3":
                    vendingMachine.dispenseProduct();
                    break;
                case "4":
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (!"4".equals(selectedOption));

        scanner.close();
    }
}

