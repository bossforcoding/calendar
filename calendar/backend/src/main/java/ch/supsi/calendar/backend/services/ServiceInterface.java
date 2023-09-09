package ch.supsi.calendar.backend.services;

import org.json.simple.JSONArray;

import java.io.File;

public interface ServiceInterface {
    boolean write(JSONArray days);

    JSONArray read();

    boolean getDatabaseLocation();

    boolean createDatabaseFile(File db);

    String getLang();

    void setLang(String lang);
}
