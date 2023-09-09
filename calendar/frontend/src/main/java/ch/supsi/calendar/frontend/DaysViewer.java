package ch.supsi.calendar.frontend;

import ch.supsi.calendar.backend.controllers.Controllers;
import ch.supsi.calendar.backend.models.Day;
import ch.supsi.calendar.frontend.utilities.Cell;
import ch.supsi.calendar.frontend.visuals.EventLabels;
import ch.supsi.calendar.frontend.visuals.MonthLabels;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.List;

public class DaysViewer {
    private Cell[] celle;


    public DaysViewer() {
        celle = new Cell[42];
    }

    public void cellGenerator(){
        for (int i=0; i<42;i++){
            celle[i] = new Cell();
        }
    }
    
    public void showDays(Controllers controllers){
        this.clean();
        List<Day> daysOfMonth = controllers.getDaysOfMonth(LocalDate.of(controllers.getYear(),controllers.getMonth()+1,1));
        int startingDayOfMonth = controllers.startingDay();

        // Fill the calendar with the days before this month
        controllers.fillPreviousDays();
        for (int i = 0; i < startingDayOfMonth - 1; i++) {
            celle[i].setLabelColor(Color.LIGHTGRAY);
            //Add day to every cell of current month
            celle[i].setDayNumber(controllers.getDaysInPreviousMonth() - startingDayOfMonth + 2 + i + "");
        }

        // Display days of this month
        int daysInCurrentMonth = controllers.daysInMonth();
        for (int i = 1; i <= daysInCurrentMonth; i++) {
            celle[i - 2 + startingDayOfMonth].setDay(new Day(LocalDate.of(controllers.getYear(),controllers.getMonth()+1,i)));
            celle[i - 2 + startingDayOfMonth].setLabelColor(Color.BLACK);
            celle[i - 2 + startingDayOfMonth].setDayNumber(i + "");
            if(daysOfMonth != null) {
                for (Day day:daysOfMonth){
                    if (day.getDate().getDayOfMonth() == i){
                        celle[i - 2 + startingDayOfMonth].setDay(day);
                    }
                }
            }
            EventLabels.eventLabelsPrinter(celle[i - 2 + startingDayOfMonth]);
        }

        // Fill the calendar with the days after this month
        int j = 1;
        for (int i = daysInCurrentMonth - 1 + startingDayOfMonth; i < 42; i++) {
            celle[i].setLabelColor(Color.LIGHTGRAY);
            celle[i].setDayNumber(j++ + "");
        }
    }

    public void clean(){
        for(int i = 0; i<celle.length; i++){
            celle[i].clean();
        }
    }


    public Cell[] getCelle() {return celle;}

    //Set a new month
    public void viewMonth(int newMonth, MonthLabels monthLabels, Controllers controllers) {
        controllers.setMonth(newMonth);
        controllers.updateCalendar();
        monthLabels.showHeader(controllers);
        clean();
        showDays(controllers);
    }

    //Set a new year
    public void viewYear(int newYear, MonthLabels monthLabels, Controllers controllers) {
        controllers.setYear(newYear);
        controllers.updateCalendar();
        monthLabels.showHeader(controllers);
        showDays(controllers);
    }

    public void nextMonth(Controllers controllers, MonthLabels monthLabels){
        int currentMonth = controllers.getMonth();
        if (currentMonth == 11) {// The next month is 0 for Jan
            viewYear(1, monthLabels, controllers);
            viewMonth(0, monthLabels, controllers);
        }else{
            viewMonth((currentMonth + 1) % 12, monthLabels, controllers);
        }
    }

    public void previousMonth(Controllers controllers, MonthLabels monthLabels){
        int currentMonth = controllers.getMonth();
        if (currentMonth == 0) { // The previous month is 11 for Dec
            viewYear((-1), monthLabels, controllers);
            viewMonth(11, monthLabels, controllers);
        } else {
            viewMonth((currentMonth - 1) % 13, monthLabels, controllers);
        }
    }
}
