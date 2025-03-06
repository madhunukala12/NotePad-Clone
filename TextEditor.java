import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextEditor implements ActionListener {
    JFrame frame;
    JTextArea textArea;
    boolean isWordWrapOn = false;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu, editMenu, formatMenu, themeMenu;
    JMenuItem newFile, openFile, saveFile, saveAsFile, exitApp;
    JMenuItem wordWrapOption;
    JMenu fontMenu, fontSizeMenu;
    JMenuItem themeLight, themeDark, themeBlue, themeGray;
    JMenuItem undoAction, redoAction;
    
    FileManager fileManager = new FileManager(this);
    FormatManager formatManager = new FormatManager(this);
    ThemeManager themeManager = new ThemeManager(this);
    EditManager editManager = new EditManager(this);
    
    UndoManager undoManager = new UndoManager();

    public TextEditor() {
        createWindow();
        createTextArea();
        createMenuBar();
        createMenuItems();
        createEditMenu();
        createFormatMenu();
        createThemeMenu();
        formatManager.selectedFont = "Arial";
        formatManager.createFont(16);
    }
    
    public static void main(String[] args) {
        new TextEditor();
    }

    private void createWindow() {
        frame = new JFrame("Madhu's Notepad");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createTextArea() {
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        formatMenu = new JMenu("Format");
        themeMenu = new JMenu("Theme");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(themeMenu);
        frame.setJMenuBar(menuBar);
    }

    private void createMenuItems() {
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        saveAsFile = new JMenuItem("Save As");
        exitApp = new JMenuItem("Exit");
        
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        saveAsFile.addActionListener(this);
        exitApp.addActionListener(this);
        
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        
        fileMenu.add(exitApp);
    }
    
    private void createEditMenu() {
        undoAction = new JMenuItem("Undo");
        redoAction = new JMenuItem("Redo");
        
        undoAction.addActionListener(this);
        redoAction.addActionListener(this);
        
        editMenu.add(undoAction);
        editMenu.add(redoAction);
    }

    private void createFormatMenu() {
        wordWrapOption = new JMenuItem("Word Wrap: Off");
        wordWrapOption.addActionListener(this);
        formatMenu.add(wordWrapOption);
    }

    private void createThemeMenu() {
        themeLight = new JMenuItem("Light Theme");
        themeDark = new JMenuItem("Dark Theme");
        themeBlue = new JMenuItem("Blue Theme");
        themeGray = new JMenuItem("Gray Theme");
        
        themeLight.addActionListener(this);
        themeDark.addActionListener(this);
        themeBlue.addActionListener(this);
        themeGray.addActionListener(this);
        
        themeMenu.add(themeLight);
        themeMenu.add(themeDark);
        themeMenu.add(themeBlue);
        themeMenu.add(themeGray);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New": fileManager.createNewFile(); break;
            case "Open": fileManager.openFile(); break;
            case "Save": fileManager.saveFile(); break;
            case "Save As": fileManager.saveFileAs(); break;
            case "Exit": fileManager.exitApplication(); break;
            case "Word Wrap: Off": formatManager.toggleWordWrap(); break;
            case "Light Theme": themeManager.changeTheme("Light"); break;
            case "Dark Theme": themeManager.changeTheme("Dark"); break;
            case "Blue Theme": themeManager.changeTheme("Blue"); break;
            case "Gray Theme": themeManager.changeTheme("Gray"); break;
            case "Undo": editManager.undo(); break;
            case "Redo": editManager.redo(); break;
        }
    }
}
