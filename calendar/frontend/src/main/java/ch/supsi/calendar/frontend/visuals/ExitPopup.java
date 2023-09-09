package ch.supsi.calendar.frontend.visuals;


import ch.supsi.calendar.backend.controllers.Controllers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;

public class ExitPopup {

    public void checkExit(Stage primaryStage, Controllers controllers){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(controllers.loadString("Exit.titolo"));
        alert.setContentText(controllers.loadString("Exit.corpo"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            primaryStage.close();
        }
    }
}
