package ch.supsi.calendar.frontend.visuals;


import ch.supsi.calendar.backend.controllers.Controllers;
import ch.supsi.calendar.frontend.DaysViewer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EditMenu {
    private Menu edit;
    private MenuItem newC;
    private MenuItem previous;
    private MenuItem next;

    public EditMenu(Controllers controllers) {
        edit = new Menu(controllers.loadString("Menu.modifica"));
        newC = new MenuItem(controllers.loadString("Menu.nuovo"));
        previous = new MenuItem(controllers.loadString("Menu.precedente"));
        next = new MenuItem(controllers.loadString("Menu.prossimo"));
        edit.getItems().add(newC);
        edit.getItems().add(previous);
        edit.getItems().add(next);
    }

    public Menu getEdit() {
        return edit;
    }

    public void onAction(DaysViewer daysViewer, Controllers controllers) {
        newC.setOnAction(e -> {
            EventMenu.eventBox(false, daysViewer, 0, 0, controllers);
        });
    }

    public void onAction(DaysViewer daysViewer, MonthLabels monthLabels, Controllers controllers) {
        //moves on to the previous month from the edit menu
        previous.setOnAction(e -> {
            daysViewer.previousMonth(controllers,monthLabels);
        });
        //moves on to the next month from the edit menu
        next.setOnAction(e -> {
            daysViewer.nextMonth(controllers,monthLabels);
        });
    }
}
