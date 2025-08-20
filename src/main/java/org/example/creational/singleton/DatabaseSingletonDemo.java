package org.example.creational.singleton;

class DatabaseConnection {

    private static DatabaseConnection instance;
    private boolean connected = false;

    private DatabaseConnection() {
        this.connected = false;
        System.out.println("---Database instance created---");
    }

    public static DatabaseConnection getInstance() {
        if(instance == null ) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public void connect() {
        if (this.connected) {
            System.out.println("A connection is already active");
            return;
        }
        System.out.println("Establishing connection...");
        this.connected = true;
        System.out.println("Database connection established");
    }

    public void disconnect() {
        if (!this.connected) {
            System.out.println("No active connection to close");
            return;
        }
        System.out.println("Closing connection...");
        this.connected = false;
        System.out.println("Database connection closed");
    }
}

public class DatabaseSingletonDemo {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.connect();

        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.connect();

        System.out.println("Are they the same: " + (db1 == db2));

        db1.disconnect();
        db2.connect();
    }
}
