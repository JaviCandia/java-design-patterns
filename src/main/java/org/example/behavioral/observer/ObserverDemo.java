package org.example.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// Observer (interface)
interface Observer {
    void notify(String videoTitle);
}

// ConcreteSubject (Publisher)
class YouTubeChannel {
    private final List<Observer> subscribers = new ArrayList<>();
    private final String name;

    public YouTubeChannel(String name) {
        this.name = name;
    }

    // Attach observer
    public void subscribe(Observer observer) {
        subscribers.add(observer);
        System.out.println("New subscriber in: " + name);
    }

    // Detach observer
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
        System.out.printf("A subscriber has unsubscribed from: %s\n", name);
    }

    // Notify all observers
    public void uploadVideo(String videoTitle) {
        System.out.printf("\n>> %s has uploaded a new video: %s\n", name, videoTitle);
        for (Observer subscriber : subscribers) {
            subscriber.notify(videoTitle);
        }
    }
}

// ConcreteObserver (Subscriber)
class Subscriber implements Observer {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void notify(String videoTitle) {
        System.out.printf("%s has been notified!\n", name);
    }
}

// Client
public class ObserverDemo {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("Cocinando con Javiersillo");

        Subscriber maribel = new Subscriber("Maribel");
        Subscriber daniel = new Subscriber("Daniel");
        Subscriber chofis = new Subscriber("Chofis");

        channel.subscribe(maribel);
        channel.subscribe(daniel);

        channel.uploadVideo("Receta de Tamales de Angular");

        channel.subscribe(chofis);

        channel.uploadVideo("Receta de React al pastor");

        channel.unsubscribe(daniel);

        channel.uploadVideo("Receta de Vue de choclo");

        channel.unsubscribe(chofis);
        channel.unsubscribe(maribel);

        channel.uploadVideo("Parrillada de NodeJS");
    }
}