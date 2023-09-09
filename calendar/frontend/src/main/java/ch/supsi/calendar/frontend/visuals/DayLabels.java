package ch.supsi.calendar.frontend.visuals;


import ch.supsi.calendar.backend.controllers.Controllers;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class DayLabels {
    private String[] dayName = new String[7];

    public void setDayLabels(GridPane gridTop) {

        for (int i = 0; i < dayName.length; i++) {
            Label day = new Label(dayName[i]);
            GridPane.setHalignment(day, HPos.CENTER);
            GridPane.setValignment(day, VPos.CENTER);
            GridPane.setHgrow(day, Priority.ALWAYS);
            GridPane.setVgrow(day, Priority.ALWAYS);
            gridTop.add(day, i, 1);
        }
    }

    public void setDayName(Controllers controllers){
        for(int i = 1; i < 8; i++){
            dayName[i - 1] = controllers.loadString("DayLabels.giorno" + i);
        }

    }
}
