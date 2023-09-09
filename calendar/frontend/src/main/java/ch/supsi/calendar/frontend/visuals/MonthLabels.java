package ch.supsi.calendar.frontend.visuals;


import ch.supsi.calendar.backend.controllers.Controllers;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthLabels {
    private Label lmonth;
    private String[] monthName = new String[12];

    public MonthLabels() {
        lmonth = new Label();
        GridPane.setHalignment(lmonth, HPos.CENTER);
        GridPane.setValignment(lmonth, VPos.CENTER);
    }

    public void setMonthName(Controllers controllers){
        for(int i = 1; i < 13; i++ ){
            monthName[i - 1] = controllers.loadString("MonthLabels.mese" + i);
        }
    }

    public Label getLmonth() {
        return lmonth;
    }

    public void showHeader(Controllers controllers) {
        lmonth.setText(monthName[controllers.getMonth()] + " " + controllers.getYear());
    }
}
