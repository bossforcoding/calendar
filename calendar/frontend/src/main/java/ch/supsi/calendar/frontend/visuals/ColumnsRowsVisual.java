package ch.supsi.calendar.frontend.visuals;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class ColumnsRowsVisual {
    public static void setColumns(GridPane grid){
        for(int i = 0; i < 7; i++){
            ColumnConstraints col = new ColumnConstraints();
            col.setMinWidth(100);
            col.setPrefWidth(1000);
            col.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(col);
            if (i == 6) {
                break;
            }
            RowConstraints row = new RowConstraints();
            row.setMinHeight(100);
            row.setPrefHeight(1000);
            row.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(row);
        }
    }
}
