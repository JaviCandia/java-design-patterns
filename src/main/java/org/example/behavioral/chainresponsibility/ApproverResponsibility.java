package org.example.behavioral.chainresponsibility;

interface Approver {
    Approver setNext(Approver approver);

    void approveRequest(int amount);
}

abstract class BaseApprover implements Approver {
    private Approver nextApprover = null;

    public Approver setNext(Approver approver) {
        this.nextApprover = approver;
        return approver;
    }

    public abstract void approveRequest(int amount);

    protected void next(int amount) {
        if (this.nextApprover != null) {
            this.nextApprover.approveRequest(amount);
            return;
        }

        System.out.println("Request rejected!");
    }
}

class Supervisor extends BaseApprover {
    @Override
    public void approveRequest(int amount) {
        if (amount <= 1000) {
            System.out.println("[Supervisor]: approves the purchase of: $" + amount);
            return;
        }

        super.next(amount);
    }
}

class Manager extends BaseApprover {
    @Override
    public void approveRequest(int amount) {
        if (amount <= 5000) {
            System.out.println("[Manager]: approves the purchase of: $" + amount);
            return;
        }

        super.next(amount);
    }
}

class Director extends BaseApprover {
    @Override
    public void approveRequest(int amount) {
        System.out.println("[Manager]: approves the purchase of: $" + amount);
    }
}

public class ApproverResponsibility {
    public static void main(String[] args) {
        Supervisor supervisor = new Supervisor();
        Manager manager = new Manager();
        Director director = new Director();

        supervisor.setNext(manager).setNext(director);

        System.out.println("Solicitud de compra de $500:");
        supervisor.approveRequest(500);

        System.out.println("\nSolicitud de compra de $3000:");
        supervisor.approveRequest(3000);

        System.out.println("\nSolicitud de compra de $7000:");
        supervisor.approveRequest(7000);
    }
}