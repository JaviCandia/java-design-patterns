package org.example.structural.proxy;

// Subject
interface Room {
    void enter(Player player);
}

class Player {
    public final String name;
    public final int level;

    public Player(String name, int level) {
        this.name = name;
        this.level = level;
    }
}

// Real Subject
class SecretRoom implements Room {
    @Override
    public void enter(Player player) {
        System.out.println("Welcome to the secret room, " + player.name);
        System.out.println("A great enemy awaits you");
    }
}

// Proxy: controls access to the real subject
class MagicPortal implements Room {
    private final Room secretRoom;

    public MagicPortal(Room room) {
        this.secretRoom = room;
    }

    @Override
    public void enter(Player player) {
        if (player.level >= 10)
            this.secretRoom.enter(player);
        else
            System.out.printf("Sorry %s, your level %d is too low, you need level 10\n", player.name, player.level);
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        Room portal = new MagicPortal(new SecretRoom()); // Proxy

        Player player1 = new Player("Adventurer A", 5);
        Player player2 = new Player("Adventurer B", 15);

        System.out.println("Adventurer A tries to enter the portal");
        portal.enter(player1);

        System.out.println("\nAdventurer B tries to enter the portal");
        portal.enter(player2);
    }
}