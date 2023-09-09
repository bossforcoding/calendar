package ch.supsi.calendar.frontend.visuals;


import ch.supsi.calendar.backend.controllers.Controllers;
import javafx.stage.FileChooser;
import java.io.File;

public class FileChooserWindow {
    private FileChooser fileChooser;

    public FileChooserWindow(Controllers controllers) {
        fileChooser = new FileChooser();
        fileChooser.setTitle(controllers.loadString("FileChooser.titolo"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+"/.calendar"));
        fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("*.json", "*.json"));
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public void setFileChooser(FileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
}
