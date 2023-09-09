package ch.supsi.calendar.backend.models;

import ch.supsi.calendar.backend.controllers.Controllers;

public enum EventType {
    LEZIONE("TipoEventi.lezione"), LABORATORIO("TipoEventi.laboratorio"),
    ESAME("TipoEventi.esame"), INCONTRO("TipoEventi.incontro"), VACANZA("TipoEventi.vacanza");
    private String name;

    EventType(String loadString) {
        this.name = loadString;
    }

    //Exceptional because we need the languages for the event's types
    public String getName(Controllers controllers) {
        return controllers.loadString(name);
    }

    //selects the type of event that the user entered in order to cast the enumeration
    public static EventType selectType(String type, Controllers controllers){
        EventType eventType=null;
        if(type.equalsIgnoreCase(EventType.ESAME.getName(controllers)))
            eventType= EventType.ESAME;
        else if(type.equalsIgnoreCase(EventType.INCONTRO.getName(controllers)))
            eventType= EventType.INCONTRO;
        else if(type.equalsIgnoreCase(EventType.VACANZA.getName(controllers)))
            eventType= EventType.VACANZA;
        else if(type.equalsIgnoreCase(EventType.LEZIONE.getName(controllers)))
            eventType= EventType.LEZIONE;
        else if(type.equalsIgnoreCase(EventType.LABORATORIO.getName(controllers)))
            eventType= EventType.LABORATORIO;
        return eventType;
    }

}
