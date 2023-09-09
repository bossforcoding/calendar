package ch.supsi.calendar.frontend.visuals;

import ch.supsi.calendar.backend.models.CalEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class EventPopup {
    //assigns a tooltip to the event label that opens when the mouse hovers over the label
    public static void createPopup(CalEvent ev, Label label) {
        Tooltip t = new Tooltip();
        t.setText(ev.toString());
        t.setTextAlignment(TextAlignment.CENTER);
        t.setFont(new Font(12));
        label.setTooltip(t);
    }

    public static void createPopup(String s, Label label) {
        Tooltip t = new Tooltip();
        t.setText(s);
        t.setTextAlignment(TextAlignment.CENTER);
        t.setFont(new Font(12));
        label.setTooltip(t);
    }
}
