import java.awt.Font;

public class FormatManager {
    TextEditor editor;
    Font arial, comicSansMS, timesNewRoman;
    String selectedFont;

    public FormatManager(TextEditor editor) {
        this.editor = editor;
    }

    public void toggleWordWrap() {
        editor.isWordWrapOn = !editor.isWordWrapOn;
        editor.textArea.setLineWrap(editor.isWordWrapOn);
        editor.textArea.setWrapStyleWord(editor.isWordWrapOn);
        editor.wordWrapOption.setText("Word Wrap: " + (editor.isWordWrapOn ? "On" : "Off"));
    }

    public void createFont(int fontSize) {
        arial = new Font("Arial", Font.PLAIN, fontSize);
        comicSansMS = new Font("Comic Sans MS", Font.PLAIN, fontSize);
        timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
        setFont(selectedFont);
    }

    public void setFont(String fontName) {
        selectedFont = fontName;
        switch (selectedFont) {
            case "Arial":
                editor.textArea.setFont(arial);
                break;
            case "Comic Sans MS":
                editor.textArea.setFont(comicSansMS);
                break;
            case "Times New Roman":
                editor.textArea.setFont(timesNewRoman);
                break;
        }
    }
}
