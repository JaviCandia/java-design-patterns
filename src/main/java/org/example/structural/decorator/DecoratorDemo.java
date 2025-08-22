package org.example.structural.decorator;

interface Notification {
    void send(String message);
}

// Plain implementation
final class BasicNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Basic Notification: " + message);
    }
}

// Abstract Decorator: wraps another Notification
abstract class NotificationDecorator implements Notification {
    protected final Notification notification;

    protected NotificationDecorator(Notification wrappee) {
        this.notification = wrappee;
    }

    @Override
    public void send(String message) {
        notification.send(message);
    }
}

// Concrete Decorator: Adds email behavior
final class EmailDecorator extends NotificationDecorator {
    public EmailDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending Email Notification: " + message);
    }
}

// Concrete Decorator: Adds SMS behavior
final class SMSDecorator extends NotificationDecorator {
    public SMSDecorator(Notification notification) {
        super(notification);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS Notification: " + message);
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        Notification notification = new BasicNotification();
        notification = new EmailDecorator(notification);
        notification = new SMSDecorator(notification);

        notification.send("¡System Alert!");
    }
}
