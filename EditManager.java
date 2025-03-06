import javax.swing.undo.UndoManager;

class EditManager {
    TextEditor editor;

    public EditManager(TextEditor editor) {
        this.editor = editor;
    }

    public void undo() {
        if (editor.undoManager.canUndo()) {
            editor.undoManager.undo();
        }
    }

    public void redo() {
        if (editor.undoManager.canRedo()) {
            editor.undoManager.redo();
        }
    }
}