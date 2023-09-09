package ch.supsi.calendar.backend.services;


import ch.supsi.calendar.backend.controllers.ControllersInterface;
import ch.supsi.calendar.backend.models.Day;
import ch.supsi.calendar.backend.models.CalEvent;
import ch.supsi.calendar.backend.models.EventType;
import ch.supsi.calendar.backend.repositories.Repository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Service implements ControllersInterface {
    private ServiceInterface service = new Repository();

    //Calendar fields
    private Calendar calendar;
    private int month;  // The specified month
    private int year;  // The specified year
    private int daysInPreviousMonth;

    //Languages Fields
    private Locale locale;
    private ResourceBundle resourceBundle;

    public Service(){
        calendar = new GregorianCalendar();
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        locale = new Locale(service.getLang());
        resourceBundle = ResourceBundle.getBundle("i18n/stringhe", locale);
    }


    /*
    File Services
     */
    @Override
    public boolean addEvent(Day day){
        JSONArray tempArray = service.read();

        if(tempArray != null){
            tempArray.removeIf(d -> ((JSONObject)d).get("Date").toString().equals(day.getDate().toString()));
        }
        else {
            tempArray = new JSONArray();
        }
        //Creating JSONArray with all events
        JSONArray events = new JSONArray();

        for (CalEvent event:day.getEvents()){
            JSONObject e = new JSONObject();
            e.put("Name", event.getName());
            e.put("EventType", event.getType().toString());
            e.put("Start", event.getStart().toString());
            e.put("End", event.getEnd().toString());

            JSONObject ev = new JSONObject();
            ev.put("Event", e);
            events.add(ev);
        }
        //Creating day
        JSONObject d = new JSONObject();
        d.put("Date", day.getDate().toString());
        d.put("Events", events);

        tempArray.add(d);
        if(service.write(tempArray)){
            System.out.println("Event created in date:" + day.getDate().toString());
            return true;
        }
        System.err.println("Error creating event!");
        return false;
    }

    @Override
    public boolean addEvent(String date, CalEvent ev) {
        JSONArray tempArray = service.read();
        JSONObject dayFound;
        JSONArray events;
        //Find day matching date
        if (tempArray != null) {
            dayFound = (JSONObject) tempArray.stream().filter(d -> ((JSONObject) d).get("Date").toString().equals(date)).findFirst().orElse(null);
            if(dayFound != null){
                events = (JSONArray)dayFound.get("Events");
                tempArray.removeIf(d -> ((JSONObject) d).get("Date").toString().equals(date));
            }
            else{
                events = new JSONArray();
            }
        } else {
            tempArray = new JSONArray();
            events = new JSONArray();
        }

        JSONObject e = new JSONObject();
        e.put("Name", ev.getName());
        e.put("EventType", ev.getType().toString());
        e.put("Start", ev.getStart().toString());
        e.put("End", ev.getEnd().toString());
        JSONObject eve = new JSONObject();
        eve.put("Event", e);
        events.add(eve);

        //Creating day
        JSONObject d = new JSONObject();
        d.put("Date", date);
        d.put("Events", events);

        tempArray.add(d);

        if(service.write(tempArray)){
            System.out.println("Event added in date: "+date);
            return true;
        }
        System.err.println("Error creating event!");
        return false;
    }

    @Override
    public boolean getDatabaseLocation() {
        return service.getDatabaseLocation();
    }

    @Override
    public List<Day> getDaysOfMonth(LocalDate currentMonth){
        List<Day> daysOfMonth = new ArrayList<>();
        JSONArray tempArray = service.read();
        if(tempArray != null){
            //Remove all days that aren't in this month and same year
            tempArray.removeIf(d -> LocalDate.parse(((JSONObject)d).get("Date").toString()).getYear() != currentMonth.getYear());
            tempArray.removeIf(d -> LocalDate.parse(((JSONObject)d).get("Date").toString()).getMonthValue() != currentMonth.getMonthValue());
            //Populate list of days
            for(Object day : tempArray){
                JSONArray events = (JSONArray)((JSONObject)day).get("Events");
                List<CalEvent> eTemp = new ArrayList<>();
                for(Object event : events){
                    JSONObject e = (JSONObject) ((JSONObject) event).get("Event");
                    eTemp.add(new CalEvent(e.get("Name").toString(), EventType.valueOf(e.get("EventType").toString()), LocalTime.parse(e.get("Start").toString()),LocalTime.parse(e.get("End").toString())));
                }
                Collections.sort(eTemp, Comparator.comparing(CalEvent::getStart));
                daysOfMonth.add(new Day(eTemp,LocalDate.parse(((JSONObject) day).get("Date").toString())));
            }
            return daysOfMonth;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean createDatabaseFile(File db){return service.createDatabaseFile(db); }

    /*
    Calendar Services
     */
    @Override
    public int startingDay() {
        // Get the day of the first day in a month
        int startingDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        if (startingDayOfMonth > 1) {
            startingDayOfMonth -= 1;
        } else {
            startingDayOfMonth = 7;
        }
        return startingDayOfMonth;
    }

    @Override
    public void fillPreviousDays() {
        Calendar cloneCalendar = (Calendar) calendar.clone();
        cloneCalendar.add(Calendar.DATE, -1); // Becomes preceding month
        daysInPreviousMonth= cloneCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getDaysInPreviousMonth() {return daysInPreviousMonth;}

    @Override
    public int daysInMonth() {return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);}

    @Override
    public int getMonth() {return month;}

    @Override
    public int getYear() {return year;}

    @Override
    public void setMonth(int month) {this.month = month;}

    @Override
    public void setYear(int year) {this.year += year;}

    @Override
    public void updateCalendar() {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
    }

    /*
    Language Services
    */
    //load a string from the resource bundle and return its
    @Override
    public String loadString(String txt) {
        return resourceBundle.getString(txt);
    }


    private void setLocale(Locale locale) {
        this.locale = locale;
        resourceBundle = ResourceBundle.getBundle("i18n/stringhe", locale);
    }

    //set the local based on the choice of user for the language of the application
    @Override
    public void selectLocale(String lang) {
        String ita = loadString("Lingua.italiano");
        String en = loadString("Lingua.inglese");
        String de = loadString("Lingua.tedesco");
        String fr = loadString("Lingua.francese");
        if (lang.equalsIgnoreCase(ita)){
            service.setLang("it");
            setLocale(Locale.ITALIAN);
        }
        else if (lang.equalsIgnoreCase(en)){
            service.setLang("en");
            setLocale(Locale.ENGLISH);
        }
        else if (lang.equalsIgnoreCase(de)){
            service.setLang("de");
            setLocale(Locale.GERMAN);
        }
        else if (lang.equalsIgnoreCase(fr)){
            service.setLang("fr");
            setLocale(Locale.FRANCE);
        }
    }
}
