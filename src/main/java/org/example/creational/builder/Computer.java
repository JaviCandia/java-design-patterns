package org.example.creational.builder;

public class Computer {
    public String cpu = "CPU - Not Defined";
    public String ram = "RAM - Not Defined";
    public String storage = "Storage - Not Defined";
    public String gpu = "Not Defined";

    public void displayConfiguration () {
        System.out.println("configuración de la computadora\n"+
                " CPU: " + cpu + "\n"+
                " RAM: " + ram + "\n" +
                " STORAGE: " + storage + "\n"+
                " GPU: " + gpu);
    }
}