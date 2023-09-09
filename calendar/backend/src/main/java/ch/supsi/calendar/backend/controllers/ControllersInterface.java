package ch.supsi.calendar.backend.controllers;

import ch.supsi.calendar.backend.models.Day;
import ch.supsi.calendar.backend.models.CalEvent;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public interface ControllersInterface {

    /*
    File Services
     */
    boolean addEvent(Day day);

    boolean addEvent(String date, CalEvent ev);

    boolean getDatabaseLocation();

    List<Day> getDaysOfMonth(LocalDate currentMonth);

    boolean createDatabaseFile(File db);

    /*
     Calendar Services
     */
    int startingDay();

    void fillPreviousDays();

    int getDaysInPreviousMonth();

    int daysInMonth();

    int getMonth();

    int getYear();

    void setMonth(int month);

    void setYear(int year);

    void updateCalendar();

    /*
     Language Services
     */
    String loadString(String txt);

    void selectLocale(String lang);


}
