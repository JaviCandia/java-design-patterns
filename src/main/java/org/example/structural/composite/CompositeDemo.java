package org.example.structural.composite;

import java.util.ArrayList;
import java.util.List;

// 1) Common component
interface FileSystemComponent {
    void showDetails(String indent);
}

// 2) Leaf
final class FsFile implements FileSystemComponent {
    private final String name;

    public FsFile(String name) {
        this.name = name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "- File: " + name);
    }
}

// 3) Composite
final class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> contents = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        contents.add(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "+ Folder: " + name);
        // Increase indentation for children
        String nextIndent = indent + " ";
        for (FileSystemComponent c : contents) {
            c.showDetails(nextIndent);
        }
    }
}

public class CompositeDemo {
    public static void main(String[] args) {
        FsFile file1 = new FsFile("archivo1.txt");
        FsFile file2 = new FsFile("archivo2.txt");
        FsFile file3 = new FsFile("archivo3.txt");
        FsFile file4 = new FsFile("archivo4.txt");

        Folder folder1 = new Folder("Carpeta 1");
        Folder folder5 = new Folder("Carpeta 5");

        folder1.add(file1);
        folder1.add(file2);

        Folder folder2 = new Folder("Carpeta 2");
        folder2.add(file3);

        Folder folder3 = new Folder("Carpeta 3");
        folder3.add(file4);

        folder2.add(folder3);
        folder2.add(folder5);

        Folder rootFolder = new Folder("Carpeta ROOT");
        rootFolder.add(folder1);
        rootFolder.add(folder2);

        // Start with empty indentation
        rootFolder.showDetails("");
    }
}
