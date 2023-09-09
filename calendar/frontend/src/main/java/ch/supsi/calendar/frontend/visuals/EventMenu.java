package ch.supsi.calendar.frontend.visuals;

import ch.supsi.calendar.backend.controllers.Controllers;
import ch.supsi.calendar.backend.models.CalEvent;
import ch.supsi.calendar.backend.models.EventType;
import ch.supsi.calendar.frontend.DaysViewer;
import ch.supsi.calendar.frontend.utilities.TimeSpinner;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class EventMenu {

    public static void eventBox(boolean doubleClick, DaysViewer daysViewer, Integer colIndex, Integer rowIndex, Controllers controllers) {
        Dialog<Event> dialogWindow = new Dialog<>();
        dialogWindow.setTitle(controllers.loadString("EventMenu.titolo"));
        dialogWindow.setWidth(300);
        dialogWindow.setResizable(false);
        //datefield offset when adding an event with "new"
        int datefield = 0;

        GridPane eventGrid = new GridPane();
        eventGrid.setPadding(new Insets(10, 10, 10, 10));
        eventGrid.setVgap(5);
        eventGrid.setHgap(5);

        DatePicker datePicker = new DatePicker();
        //On calendar new Event
        if (!doubleClick) {
            Label date = new Label(controllers.loadString("EventMenu.data"));
            eventGrid.add(date, 0, 0);
            eventGrid.add(datePicker, 1, 0);
            datefield = 1;
        }
        //creating the menu to add an event
        Label name = new Label(controllers.loadString("EventMenu.nome"));
        TextField fname = new TextField();
        Label start = new Label(controllers.loadString("EventMenu.inizio"));
        TimeSpinner timeSpinnerStart = new TimeSpinner();
        Label end = new Label(controllers.loadString("EventMenu.fine"));
        TimeSpinner timeSpinnerStop = new TimeSpinner();
        Label category = new Label(controllers.loadString("EventMenu.categoria"));
        ComboBox catBox = new ComboBox();
        catBox.getItems().addAll(EventType.ESAME.getName(controllers), EventType.INCONTRO.getName(controllers), EventType.LABORATORIO.getName(controllers), EventType.LEZIONE.getName(controllers), EventType.VACANZA.getName(controllers));
        Label errorName = new Label();
        Label errorText = new Label();
        eventGrid.add(name, 0, datefield);
        eventGrid.add(fname, 1, datefield);
        eventGrid.add(start, 0, 1 + datefield);
        eventGrid.add(timeSpinnerStart, 1, 1 + datefield);
        eventGrid.add(end, 0, 2 + datefield);
        eventGrid.add(timeSpinnerStop, 1, 2 + datefield);
        eventGrid.add(category, 0, 3 + datefield);
        eventGrid.add(catBox, 1, 3 + datefield);
        eventGrid.add(errorName, 0, 4 + datefield);
        eventGrid.add(errorText, 1, 4 + datefield);

        dialogWindow.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        Button finish = (Button) dialogWindow.getDialogPane().lookupButton(ButtonType.OK);
        //adding an event if I double-click the cell of the day
        if (doubleClick) {
            finish.addEventFilter(ActionEvent.ACTION, event -> {
                if (fname.getText() != null && timeSpinnerStart.getValue().isBefore(timeSpinnerStop.getValue()) && catBox.getValue() != null) {
                    String cat= (String) catBox.getValue();
                    EventType type= EventType.selectType(cat, controllers);
                    daysViewer.getCelle()[colIndex + 7 * rowIndex].getDay().addEvent(new CalEvent(fname.getText(), type, timeSpinnerStart.getValue(), timeSpinnerStop.getValue()));
                    if (controllers.addEvent(daysViewer.getCelle()[colIndex + 7 * rowIndex].getDay())) {
                        daysViewer.showDays(controllers);
                    }
                    dialogWindow.close();
                }
                else{
                    errorText.setTextFill(Color.RED);
                    errorText.setText(controllers.loadString("Errore.creazione"));
                    event.consume();
                }
            });
        } else {
            finish.addEventFilter(ActionEvent.ACTION, event -> {
                if (fname.getText() != null && timeSpinnerStart.getValue().isBefore(timeSpinnerStop.getValue()) && catBox.getValue() != null && datePicker.getValue() != null) {
                    String cat= (String) catBox.getValue();
                    EventType type = EventType.selectType(cat, controllers);
                    CalEvent ev = new CalEvent(fname.getText(), type , timeSpinnerStart.getValue(), timeSpinnerStop.getValue());
                    controllers.addEvent(datePicker.getValue().toString(), ev);
                    daysViewer.showDays(controllers);
                    dialogWindow.close();
                }
                else{
                    errorText.setTextFill(Color.RED);
                    errorText.setText(controllers.loadString("Errore.creazione"));
                    event.consume();
                }
            });
        }
        dialogWindow.getDialogPane().setContent(eventGrid);
        dialogWindow.show();
    }
}
