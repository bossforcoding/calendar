package ch.supsi.calendar.frontend;


import ch.supsi.calendar.backend.controllers.Controllers;
import ch.supsi.calendar.backend.models.CalEvent;
import ch.supsi.calendar.frontend.visuals.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ApplicationFx extends Application {
    private DaysViewer daysViewer = new DaysViewer();
    private Controllers controllers = new Controllers();

    @Override
    public void start(Stage primaryStage) {
        CalEvent.setControllers(controllers);

        if(!controllers.getDatabaseLocation()){
            FileChooserWindow fc = new FileChooserWindow(controllers);
            if(!controllers.createDatabaseFile(fc.getFileChooser().showSaveDialog(primaryStage))){
                System.err.println("Error: file not created!");
                primaryStage.close();
            }
        }

        primaryStage.setTitle(controllers.loadString("Stage.titolo"));
        primaryStage.setResizable(false);

        //Label month
        MonthLabels monthLabel = new MonthLabels();
        monthLabel.setMonthName(controllers);

        //Main Box
        VBox box = new VBox();
        //Menu Bar
        MenuBar bar = new MenuBar();

        //Menu File
        FileMenu fileMenu = new FileMenu(controllers);
        bar.getMenus().add(fileMenu.getFile());
        fileMenu.onAction(primaryStage, controllers);

        //Menu Edit
        EditMenu editMenu = new EditMenu(controllers);
        bar.getMenus().add(editMenu.getEdit());
        editMenu.onAction(daysViewer, controllers);
        editMenu.onAction(daysViewer,monthLabel, controllers);

        //Menu Help
        HelpMenu helpMenu=new HelpMenu(controllers);
        bar.getMenus().add(helpMenu.getEdit());
        helpMenu.onAction(primaryStage, controllers);

        //Add MenuBar to Box
        box.getChildren().add(bar);
        box.setVgrow(bar, Priority.NEVER);

        //SplitPane
        SplitPaneVisual split = new SplitPaneVisual();


        //Gridpane Top
        GridPane grid_top = new GridPane();
        grid_top.alignmentProperty().set(Pos.CENTER);


        //Arrow Buttons
        ArrowsVisual arrows = new ArrowsVisual();

        //Day Labels
        DayLabels dayLabels = new DayLabels();
        dayLabels.setDayName(controllers);
        dayLabels.setDayLabels(grid_top);


        //Add to first row of GridPane
        grid_top.add(arrows.getLeft(),2,0);
        grid_top.add(monthLabel.getLmonth(),3,0);
        grid_top.add(arrows.getRight(),4,0);

        //GridPane
        GridVisual gridVisual = new GridVisual();

        daysViewer.cellGenerator();

        //Columns
        ColumnsRowsVisual.setColumns(gridVisual.getGrid());


        //Fill labels to grid
        for (int i = 0; i < 42; i++) {
            gridVisual.getGrid().add(daysViewer.getCelle()[i], i % 7, i / 7);
        }

        // Set current month and year
        controllers.updateCalendar();

        // Show calendar
        monthLabel.showHeader(controllers);
        daysViewer.showDays(controllers);

        //Add Upper and Lower
        split.getMainSplit().getItems().add(grid_top);
        split.getMainSplit().getItems().add(gridVisual.getGrid());

        //Add Split
        box.getChildren().add(split.getMainSplit());

        //Open
        Scene scene = new Scene(box, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        split.getMainSplit().lookupAll(".split-pane-divider").stream()
                .forEach(div ->  div.setMouseTransparent(true));

        arrows.leftAction(daysViewer, monthLabel, controllers);

        arrows.rightAction(daysViewer, monthLabel, controllers);

        gridVisual.onAction(daysViewer, monthLabel, controllers);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
