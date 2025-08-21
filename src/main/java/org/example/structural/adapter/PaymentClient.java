package org.example.structural.adapter;

interface PaymentProcessor {
    void processPayment(double amount);
}

class PayPalService {
    public void sendPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " with PayPal");
    }
}

class StripeService {
    public void makeCharge(double amount) {
        System.out.println("Processing payment of $" + amount + " with Stripe");
    }
}

class MercadoPagoService {
    public void pay(double amount) {
        System.out.println("Processing payment of $" + amount + " with MercadoPago");
    }
}

class PayPalAdapter implements PaymentProcessor {
    PayPalService service;

    public PayPalAdapter(PayPalService service) {
        this.service = service;
    }

    @Override
    public void processPayment(double amount) {
        service.sendPayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    StripeService service;

    public StripeAdapter(StripeService service) {
        this.service = service;
    }

    @Override
    public void processPayment(double amount) {
        service.makeCharge(amount);
    }
}

class MercadoPagoAdapter implements PaymentProcessor {
    MercadoPagoService service;

    public MercadoPagoAdapter (MercadoPagoService service) {
        this.service = service;
    }

    @Override
    public void processPayment(double amount) {
        service.pay(amount);
    }
}

class PaymentClient {
    public static void main(String[] args) {
        double paymentAmount = 100;

            PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalService());
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeService());
        PaymentProcessor mercadoPagoProcessor = new MercadoPagoAdapter(new MercadoPagoService());

        System.out.println("Using PayPal:");
        paypalProcessor.processPayment(paymentAmount);

        System.out.println("\nUsing Stripe:");
        stripeProcessor.processPayment(paymentAmount);

        System.out.println("\nUsing MercadoPago:");
        mercadoPagoProcessor.processPayment(paymentAmount);
    }
}
