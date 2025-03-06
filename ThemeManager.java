import java.awt.*;

class ThemeManager {
    TextEditor editor;

    public ThemeManager(TextEditor editor) {
        this.editor = editor;
    }

    public void changeTheme(String theme) {
        switch (theme) {
            case "Light":
                applyTheme(Color.WHITE, Color.BLACK);
                break;
            case "Dark":
                applyTheme(Color.BLACK, Color.WHITE);
                break;
            case "Blue":
                applyTheme(new Color(173, 216, 230), Color.BLACK);
                break;
            case "Gray":
                applyTheme(Color.GRAY, Color.WHITE);
                break;
        }
    }

    private void applyTheme(Color bgColor, Color textColor) {
        editor.frame.getContentPane().setBackground(bgColor);
        editor.textArea.setBackground(bgColor);
        editor.textArea.setForeground(textColor);
    }
}
