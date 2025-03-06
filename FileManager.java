import java.awt.*;
import java.awt.FileDialog;
import java.io.*;
import javax.swing.*;

class FileManager {
    TextEditor editor;
    String fileName;
    String fileAddress;

    public FileManager(TextEditor editor) {
        this.editor = editor;
    }

    public void createNewFile() {
        editor.textArea.setText("");
        editor.frame.setTitle("New");
        fileAddress = null;
        fileName = null;
    }

    public void openFile() {
        FileDialog fd = new FileDialog(editor.frame, "Select", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            editor.frame.setTitle(fileName);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                editor.textArea.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        if (fileName == null) {
            saveFileAs();
        } else {
            try (FileWriter fw = new FileWriter(fileAddress + fileName)) {
                editor.frame.setTitle(fileName);
                fw.write(editor.textArea.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFileAs() {
        FileDialog fd = new FileDialog(editor.frame, "Save", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            editor.frame.setTitle(fileName);
        }
        try (FileWriter fw = new FileWriter(fileAddress + fileName)) {
            fw.write(editor.textArea.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exitApplication() {
        System.exit(0);
    }
}
