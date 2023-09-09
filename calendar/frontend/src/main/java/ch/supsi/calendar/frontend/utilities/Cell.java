package ch.supsi.calendar.frontend.utilities;

import ch.supsi.calendar.backend.models.Day;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Cell extends VBox {
    private Label dayNumber;
    private Day day;
    private VBox eventsBox;

    public Cell() {
        this.dayNumber = new Label();
        this.getChildren().add(dayNumber);
        eventsBox = new VBox();
        this.getChildren().add(eventsBox);
    }

    public VBox getEventsBox() {
        return eventsBox;
    }

    public void setDayNumber(String text) {
        dayNumber.setText(text);
    }

    public void setLabelColor(Color colore) {
        dayNumber.setTextFill(colore);
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void clean() {
        eventsBox.getChildren().clear();
    }

    public void addEventLabel(Label l) {
        eventsBox.getChildren().add(l);
    }

}
