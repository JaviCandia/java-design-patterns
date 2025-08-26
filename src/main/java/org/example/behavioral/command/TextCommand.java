package org.example.behavioral.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

interface Command2 {
    void execute();
}

// Receiver
class TextEditor {
    private final Stack<String> history = new Stack<>();
    private String text = "";
    private String clipboard = "";

    void type(String text) {
        this.history.push(this.text);
        this.text += text;
    }

    void copy() {
        this.clipboard = this.text;
        System.out.println("Text copied to clipboard: \n\"" + this.clipboard + "\"");
    }

    void paste() {
        this.history.push(this.text);
        this.text += this.clipboard;
        System.out.println("Text after paste: \n\"" + this.text + "\"");
    }

    void undo() {
        if (!this.history.isEmpty()) {
            this.text = this.history.pop();
            System.out.println("Text after undo: \n\"" + this.text + "\"");
            return;
        }
        System.out.println("There is nothing to undo.");
    }

    String getText() {
        return this.text;
    }
}

// Concrete Commands
record CopyCommand(TextEditor editor) implements Command2 {

    @Override
    public void execute() {
        this.editor.copy();
    }
}

record PasteCommand(TextEditor editor) implements Command2 {

    @Override
    public void execute() {
        this.editor.paste();
    }
}

record UndoCommand(TextEditor editor) implements Command2 {

    @Override
    public void execute() {
        this.editor.undo();
    }
}

// Invoker
class Toolbar {
    private final Map<String, Command2> commands = new HashMap<>();

    void setCommand(String button, Command2 command) {
        this.commands.put(button, command);
    }

    void clickButton(String button) {
        Command2 command = this.commands.get(button);
        if (command != null) {
            command.execute();
            return;
        }
        System.err.println("No command has been assigned to the button \"" + button + "\"");
    }
}

public class TextCommand {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Toolbar toolbar = new Toolbar();

        // Create commands for the editor
        Command2 copyCommand = new CopyCommand(editor);
        Command2 pasteCommand = new PasteCommand(editor);
        Command2 undoCommand = new UndoCommand(editor);

        // Assign commands to the toolbar buttons
        toolbar.setCommand("copy", copyCommand);
        toolbar.setCommand("paste", pasteCommand);
        toolbar.setCommand("undo", undoCommand);

        // Text editing simulation
        editor.type("Hello World");
        editor.type("!");
        System.out.printf("Current text: '%s'\n", editor.getText());

        // Use the toolbar
        System.out.println("\nCopying text:");
        toolbar.clickButton("copy");

        System.out.println("\nPasting text:");
        toolbar.clickButton("paste");

        System.out.println("\nUndoing last action:");
        toolbar.clickButton("undo");

        System.out.println("\nUndoing again:");
        toolbar.clickButton("undo");

        System.out.printf("\nFinal text: '%s'\n", editor.getText());
    }
}