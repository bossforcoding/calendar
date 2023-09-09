package ch.supsi.calendar.frontend.visuals;


import ch.supsi.calendar.backend.controllers.Controllers;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class FileMenu {
    private Menu file;
    private MenuItem exit;

    public FileMenu(Controllers controllers) {
        file = new Menu("File");
        exit = new MenuItem(controllers.loadString("Menu.esci"));
        file.getItems().add(exit);
    }

    public Menu getFile() {
        return file;
    }

    public void onAction(Stage primaryStage, Controllers controllers){
        //opens a popup to confirm that you are leaving the application
        exit.setOnAction(e->{
            ExitPopup exit = new ExitPopup();
            exit.checkExit(primaryStage, controllers);
        });
    }
}
