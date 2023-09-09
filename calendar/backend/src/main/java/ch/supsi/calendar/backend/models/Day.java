package ch.supsi.calendar.backend.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Day {
    //List of events
    private List<CalEvent> events;
    //Date of current day
    private LocalDate date;

    //Constructor
    public Day(List<CalEvent> events, LocalDate date) {
        this.events = events;
        this.date = date;
    }

    //Constructor
    public Day(LocalDate date) {
        this.events = new ArrayList<>();
        this.date = date;
    }

    /*
     * Getters
     */
    public List<CalEvent> getEvents() {
        return events;
    }

    public LocalDate getDate() {
        return date;
    }

    public void addEvent(CalEvent e){
        events.add(e);
    }


}
