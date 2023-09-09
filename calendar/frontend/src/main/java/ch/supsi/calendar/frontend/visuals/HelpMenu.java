package ch.supsi.calendar.frontend.visuals;


import ch.supsi.calendar.backend.controllers.Controllers;
import ch.supsi.calendar.frontend.utilities.AppInfoLoader;
import ch.supsi.calendar.frontend.ApplicationFx;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Properties;


public class HelpMenu {
    private Menu help;
    private MenuItem about;
    private MenuItem language;

    public HelpMenu(Controllers controllers) {
        help = new Menu(controllers.loadString("Menu.aiuto"));
        about = new MenuItem(controllers.loadString("Menu.riguardo"));
        language = new MenuItem(controllers.loadString("Menu.lingua"));
        help.getItems().add(about);
        help.getItems().add(language);
    }

    public Menu getEdit() {
        return help;
    }

    public void onAction(Stage primaryStage, Controllers controllers) {
        //opens an alert to display application name, version and latest build
        about.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(controllers.loadString("Alert.titolo"));
            Properties appInfo = AppInfoLoader.getAppInfo();
            alert.setContentText(controllers.loadString("Alert.testo") + "\n" + controllers.loadString("Alert.versione") + " " + appInfo.getProperty("project.version") + "\nbuild " + appInfo.getProperty("project.build") + " (UTC)");
            alert.show();
        });

        //opens a menu to select the language of the application
        language.setOnAction(e -> {
            Dialog dialogWindow = new Dialog<>();
            dialogWindow.setTitle(controllers.loadString("Menu.selezionaLingua"));
            dialogWindow.setWidth(300);
            dialogWindow.setResizable(false);
            GridPane eventGrid = new GridPane();
            eventGrid.setPadding(new Insets(10, 10, 10, 10));
            eventGrid.setVgap(5);
            eventGrid.setHgap(5);
            ComboBox catBox = new ComboBox();
            String ita = controllers.loadString("Lingua.italiano");
            String en = controllers.loadString("Lingua.inglese");
            String de = controllers.loadString("Lingua.tedesco");
            String fr = controllers.loadString("Lingua.francese");
            catBox.getItems().addAll(ita, en, de, fr);
            eventGrid.add(catBox, 0, 0);
            dialogWindow.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            Button finish = (Button) dialogWindow.getDialogPane().lookupButton(ButtonType.OK);

            finish.addEventFilter(ActionEvent.ACTION, event -> {
                String lang = catBox.getValue().toString();
                controllers.selectLocale(lang);
                //closes the stage and reopens the application in the selected language
                primaryStage.close();
                Platform.runLater(() -> new ApplicationFx().start(primaryStage));
            });
            dialogWindow.getDialogPane().setContent(eventGrid);
            dialogWindow.show();
        });
    }
}
