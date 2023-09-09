package ch.supsi.calendar.frontend.visuals;

import ch.supsi.calendar.backend.controllers.Controllers;
import ch.supsi.calendar.frontend.DaysViewer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GridVisual {
    private GridPane grid;

    public GridVisual() {
        grid = new GridPane();
        grid.gridLinesVisibleProperty().set(true);
        grid.setAlignment(Pos.CENTER);;
    }

    public GridPane getGrid() {
        return grid;
    }

    public void onAction(DaysViewer daysViewer, MonthLabels monthLabel, Controllers controllers){
        grid.setOnMouseClicked(e -> {
            Node clickedNode = e.getPickResult().getIntersectedNode();
            if (clickedNode != grid && e.getClickCount() ==2) {
                // click on descendant node
                Node parent = clickedNode.getParent();
                while (parent != grid) {
                    clickedNode = parent;
                    parent = clickedNode.getParent();
                }
                Integer colIndex = GridPane.getColumnIndex(clickedNode);
                Integer rowIndex = GridPane.getRowIndex(clickedNode);
                int pos = ((rowIndex*7)+colIndex);
                if (pos < controllers.startingDay()){
                    //If clicked on a day of the previous month, will move to that month
                    daysViewer.previousMonth(controllers,monthLabel);
                }else if(pos>=(controllers.daysInMonth()+controllers.startingDay()-1)){
                    //If clicked on a day of the next month, will move to that month
                    daysViewer.nextMonth(controllers,monthLabel);
                }else{
                    EventMenu.eventBox(true, daysViewer, colIndex, rowIndex, controllers);
                }
            }
        });
    }
}
