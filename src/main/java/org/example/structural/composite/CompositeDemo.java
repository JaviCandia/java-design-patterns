package org.example.structural.composite;

import java.util.ArrayList;
import java.util.List;

// Composite Pattern: common component interface for leaf and composite
interface FileSystemComponent {
    void showDetails(String indent);
}

// Leaf: atomic object with no children
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

// Composite: holds children of the same base type (can be leaves or other composites)
final class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> contents = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    // Composite operation: add child to this node
    public void add(FileSystemComponent component) {
        contents.add(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "> Folder: " + name);

        String nextIndent = indent + "   ";
        for (FileSystemComponent content : contents) {
            content.showDetails(nextIndent);
        }
    }
}

public class CompositeDemo {
    public static void main(String[] args) {
        // Building leaves
        FsFile file1 = new FsFile("file1.txt");
        FsFile file2 = new FsFile("file2.txt");
        FsFile file3 = new FsFile("file3.txt");
        FsFile file4 = new FsFile("file4.txt");

        // Building Composites
        Folder folder1 = new Folder("1");
        folder1.add(file1);
        folder1.add(file2);

        Folder folder2 = new Folder("2");
        folder2.add(file3);

        Folder folder3 = new Folder("3");
        folder3.add(file4);

        // Composite can contain both leaves and other composites
        Folder folder4 = new Folder("4");
        folder2.add(folder3);
        folder2.add(folder4);

        Folder rootFolder = new Folder("ROOT");
        rootFolder.add(folder1);
        rootFolder.add(folder2);

        // Entry point: print the whole tree
        rootFolder.showDetails("");
    }
}