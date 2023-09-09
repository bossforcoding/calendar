package ch.supsi.calendar.backend.models;

import ch.supsi.calendar.backend.controllers.Controllers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CalEvent {
    private String name;
    private EventType type;
    private LocalTime start;
    private LocalTime end;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    //Exceptional because we need the languages for the event's types
    private static Controllers controllers;

    //Constructor
    public CalEvent(String name, EventType type, LocalTime start, LocalTime end) {
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    /*
     * Setters
     */
    public static void setControllers(Controllers controllers) {
        CalEvent.controllers = controllers;
    }

    /*
     * Getters
     */
    public String getName() {
        return name;
    }

    public EventType getType() {
        return type;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return name +
                ", " + type.getName(controllers) +
                ", " + formatter.format(start) +
                ", " + formatter.format(end);
    }
}
