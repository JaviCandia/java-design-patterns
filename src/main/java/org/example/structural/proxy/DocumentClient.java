package org.example.structural.proxy;

// Subject
interface Document {
    void displayContent(User user);
}

class User {
    private final String name;
    private final String role; // "admin" | "user"

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }
}

// Real Subject
record ConfidentialDocument(String content) implements Document {

    public void displayContent(User user) {
        System.out.println("Contenido del documento: \n" + this.content + "\n");
    }
}

// 3. Proxy Class - DocumentProxy
class DocumentProxy implements Document {
    private final Document document;

    DocumentProxy(Document document) {
        this.document = document;
    }

    @Override
    public void displayContent(User user) {
        if ( "admin".equals(user.getRole()) ) {
            this.document.displayContent(user);
        } else {
            System.out.printf("Access Denied. %s you don't have permission to view this document\n", user.getName());
        }
    }
}

public class DocumentClient {
    public static void main(String[] args) {
        ConfidentialDocument confidentialDoc = new ConfidentialDocument(
                "This Document has confidential content."
        );
        DocumentProxy proxy = new DocumentProxy(confidentialDoc);

        User user1 = new User("Juan", "user");
        User user2 = new User("Ana", "admin");

        System.out.println("User 1 trying to access:");
        proxy.displayContent(user1);

        System.out.println("\nUser 2 trying to access:");
        proxy.displayContent(user2);
    }
}