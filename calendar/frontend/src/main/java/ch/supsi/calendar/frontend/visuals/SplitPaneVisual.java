package ch.supsi.calendar.frontend.visuals;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

public class SplitPaneVisual {
    SplitPane mainSplit;

    public SplitPaneVisual() {
        mainSplit = new SplitPane();
        mainSplit.orientationProperty().set(Orientation.VERTICAL);
        mainSplit.setDividerPositions(0.15);
        mainSplit.prefHeight(373);
        mainSplit.prefWidth(640);
    }

    public SplitPane getMainSplit() {
        return mainSplit;
    }
}
