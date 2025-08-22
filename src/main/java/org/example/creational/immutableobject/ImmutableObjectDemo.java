package org.example.creational.immutableobject;

import java.util.ArrayList;
import java.util.List;

class CodeEditorState {
    // Immutable: all fields are final and there are no setters
    private final String content;
    private final int cursorPosition;
    private final boolean unsavedChanges;

    public CodeEditorState(String content, int cursorPosition, boolean unsavedChanges) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.unsavedChanges = unsavedChanges;
    }

    // Immutable update pattern: return a NEW instance rather than mutating the current one
    public CodeEditorState copyWith(String content, Integer cursorPosition, Boolean unsavedChanges) {
        return new CodeEditorState(
                (content != null) ? content : this.content,
                (cursorPosition != null) ? cursorPosition : this.cursorPosition,
                (unsavedChanges != null) ? unsavedChanges : this.unsavedChanges
        );
    }

    public void displayState() {
        System.out.printf("""
                ==> Editor State <==
                Cursor Position: %d
                Unsaved changes: %s
                Content:
                %s
                """, this.cursorPosition, this.unsavedChanges, this.content);
    }

}

class CodeEditorHistory {
    private List<CodeEditorState> history = new ArrayList<>();
    private int currentIndex = -1;

    public void save(CodeEditorState state) {
        if (this.currentIndex < this.history.size() - 1) {
            this.history = this.history.subList(0, this.currentIndex + 1);
        }
        this.history.add(state);
        this.currentIndex++;
    }

    public CodeEditorState undo() {
        if (this.currentIndex > 0) {
            this.currentIndex--;
            return this.history.get(this.currentIndex);
        }
        return null;
    }

    public CodeEditorState redo() {
        if (this.currentIndex < this.history.size() - 1) {
            this.currentIndex++;
            return this.history.get(this.currentIndex);
        }
        return null;
    }
}

public class ImmutableObjectDemo {
    public static void main(String[] args) {
        CodeEditorHistory history = new CodeEditorHistory();
        CodeEditorState editorState = new CodeEditorState("console.log('Hello World');", 2, false);

        System.out.println("\nInitial State");
        history.save(editorState);
        editorState.displayState();

        System.out.println("\nAfter the first change");
        editorState = editorState.copyWith(
                "console.log('Hello World'); \nconsole.log('New Line');",
                3,
                true
        );
        history.save(editorState);
        editorState.displayState();

        // Undo changes
        editorState = history.undo();
        if (editorState != null) {
            System.out.println("\nAfter undo");
            editorState.displayState();
        }

        // Redo changes
        editorState = history.redo();
        if (editorState != null) {
            System.out.println("\nAfter redo");
            editorState.displayState();
        }
    }
}
