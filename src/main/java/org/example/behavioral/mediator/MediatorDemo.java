package org.example.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

// Mediator: centralizes communication between Users
class ChatRoom {
    private final List<User> users = new ArrayList<>();
    private final String title;

    public ChatRoom(String title) {
        this.title = title;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(User sender, String message) {
        // Send to every user except the sender
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(sender, message);
            }
        }
    }

    public String getTitle() {
        return title;
    }
}

// Colleague: communicates only through the Mediator (ChatRoom)
class User {
    private final String username;
    private final ChatRoom chatRoom;

    public User(String username, ChatRoom chatRoom) {
        this.username = username;
        this.chatRoom = chatRoom;
        chatRoom.addUser(this);
    }

    public void sendMessage(String message) {
        System.out.println("\n" + username + " sends: " + message);
        chatRoom.sendMessage(this, message);
    }

    public void receiveMessage(User sender, String message) {
        System.out.println(username + " receives from " + sender.username + ": " + message);
    }
}

public class MediatorDemo {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom("Grupo de trabajo");

        User user1 = new User("Fernando", chatRoom);
        User user2 = new User("Gastón", chatRoom);
        User user3 = new User("Mariangel", chatRoom);

        user1.sendMessage("¡Hola a todos!");
        user2.sendMessage("Hola Fernando, ¿cómo estás?");
        user3.sendMessage("Hola Fernando, Gastón, ¿cómo están?");
        System.out.println();
    }
}