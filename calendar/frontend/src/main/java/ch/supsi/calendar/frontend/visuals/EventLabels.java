package ch.supsi.calendar.frontend.visuals;

import ch.supsi.calendar.backend.models.CalEvent;
import ch.supsi.calendar.frontend.utilities.Cell;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.List;

public class EventLabels {

    public static void eventLabelsPrinter(Cell c) {
        if (c.getDay().getEvents() != null) {
            List<CalEvent> eventi = c.getDay().getEvents();
            if (eventi.size()>3){
                int i;
                for (i=0; i<3; i++) {
                    CalEvent e = eventi.get(i);
                    Label label = setColor(e);
                    //create event popup of the label added
                    EventPopup.createPopup(e, label);
                    c.addEventLabel(label);
                }
                Label more = new Label("...");
                StringBuilder sb = new StringBuilder();
                while (i<eventi.size()){
                    CalEvent e = eventi.get(i);
                    sb.append(e.toString()+"\n");
                    i++;
                }
                //create event popup of the label added
                EventPopup.createPopup(sb.toString(),more);
                more.setFont(new Font(24));
                c.addEventLabel(more);
            }else{
                eventi.forEach(e -> {
                    Label label = setColor(e);
                    //create event popup of the label added
                    EventPopup.createPopup(e, label);
                    c.addEventLabel(label);
                });
            }
        }
    }

    private static Label setColor(CalEvent e){
        Label label = new Label(e.toString());

        //color event label based on the selected event type
        switch (e.getType()) {
            case LEZIONE:
                label.setStyle("-fx-background-color: lightgreen;");
                break;
            case LABORATORIO:
                label.setStyle("-fx-background-color: red;");
                break;
            case ESAME:
                label.setStyle("-fx-background-color: lightblue;");
                break;
            case INCONTRO:
                label.setStyle("-fx-background-color: yellow;");
                break;
            case VACANZA:
                label.setStyle("-fx-background-color: pink;");
                break;
        }
        return label;
    }
}
