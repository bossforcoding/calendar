package ch.supsi.calendar.backend.controllers;

import ch.supsi.calendar.backend.models.Day;
import ch.supsi.calendar.backend.models.CalEvent;
import ch.supsi.calendar.backend.services.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Controllers {
    private ControllersInterface controller = new Service();

    /*
    File Methods related
     */
    public boolean addEvent(Day day){ return controller.addEvent(day); }

    public boolean addEvent(String date, CalEvent ev){ return controller.addEvent(date, ev);}

    public boolean getDatabaseLocation(){ return controller.getDatabaseLocation();}

    public List<Day> getDaysOfMonth(LocalDate currentMonth){ return controller.getDaysOfMonth( currentMonth);}

    public boolean createDatabaseFile(File db){return controller.createDatabaseFile(db);}
    /*
    Calendar Methods related
     */
    public int startingDay() {return controller.startingDay();}

    public void fillPreviousDays(){controller.fillPreviousDays();}

    public int getDaysInPreviousMonth(){return controller.getDaysInPreviousMonth();}

    public int daysInMonth(){return controller.daysInMonth();}

    public int getMonth(){return controller.getMonth();}

    public int getYear(){return controller.getYear();}

    public void setMonth(int month){controller.setMonth(month);}

    public void setYear(int year){controller.setYear(year);}

    public void updateCalendar(){controller.updateCalendar();}

    /*
    Languages Methods related
     */
    public String loadString(String txt){return controller.loadString(txt);}


    public void selectLocale(String lang){controller.selectLocale(lang);}



}
