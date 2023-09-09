package ch.supsi.calendar.frontend.visuals;

import ch.supsi.calendar.backend.controllers.Controllers;
import ch.supsi.calendar.frontend.DaysViewer;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ArrowsVisual {
    private Button left;
    private Button right;

    //creating two buttons for scrolling months in the gui
    public ArrowsVisual() {
        left = new Button("<<");
        left.mnemonicParsingProperty().set(false);
        GridPane.setHalignment(left, HPos.RIGHT);
        GridPane.setValignment(left, VPos.CENTER);
        right = new Button(">>");
        right.mnemonicParsingProperty().set(false);
        GridPane.setHalignment(right, HPos.LEFT);
        GridPane.setValignment(right, VPos.CENTER);
    }

    public Button getLeft() {
        return left;
    }

    public Button getRight() {
        return right;
    }

    public void leftAction(DaysViewer daysViewer, MonthLabels monthLabels, Controllers controllers){
        //scroll arrows to the previous month
        left.setOnAction(e -> {
            daysViewer.previousMonth(controllers,monthLabels);
        });
    }

    public void rightAction(DaysViewer daysViewer, MonthLabels monthLabels, Controllers controllers){
        //scroll arrows to the next month
        right.setOnAction(e -> {
            daysViewer.nextMonth(controllers,monthLabels);
        });
    }
}
