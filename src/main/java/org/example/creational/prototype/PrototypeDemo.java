package org.example.creational.prototype;

class Document {
    public String title;
    final private String content;
    public String author;

    public Document(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Prototype Pattern: copy constructor used to clone an existing Document
    public Document(Document document) {
        this.title = document.title;
        this.content = document.content;
        this.author = document.author;
    }

    // Sintactic Sugar for Prototype Pattern
    //public Document clone() {
    //    return new Document(this);
    //}

    void displayInfo() {
        System.out.println("Title: " + this.title + "\n" +
                "Content: " + this.content + "\n" +
                "Author: " + this.author + "\n");
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        Document document1 = new Document("Maravillas del mundo", "Ciencia", "Javiersillo");
        document1.displayInfo();

        // Sintactic Sugar for Prototype Pattern
        //Document document2 = document1.clone();

        // Usually they use Copy Constructor for Java
        Document document2 = new Document(document1);
        document2.title = "Chistes y cuentos";
        document2.displayInfo();

        document1.displayInfo();
    }
}
