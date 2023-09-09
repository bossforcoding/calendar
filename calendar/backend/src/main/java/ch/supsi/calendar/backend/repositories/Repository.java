package ch.supsi.calendar.backend.repositories;

import ch.supsi.calendar.backend.services.ServiceInterface;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Scanner;


public class Repository implements ServiceInterface {
    private String filePosition;
    private File userPrefs;
    private String lang = "it";
    private String fileUserPreferences = System.getProperty("user.home") + "/.calendar";

    public Repository() {
        File folder = new File(fileUserPreferences);
        folder.mkdir();
        fileUserPreferences += "/user.prefs";
        getDatabaseLocation();
    }


    //Method that writes to a file a json array
    @Override
    public boolean write(JSONArray days) {
        try {
            FileWriter file = new FileWriter(filePosition);
            file.write(days.toJSONString());
            file.flush();
            file.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Method that read from file
    @Override
    public JSONArray read() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePosition))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;
            return employeeList;
        } catch (IOException | ParseException e) {
            return null;
        }
    }

    //Method that gets the database location and language preference
    @Override
    public boolean getDatabaseLocation(){
        String dbLocation = "";
        userPrefs = new File(fileUserPreferences);
        if (userPrefs.exists()){
            //Preferences file exists
            try {
                //Get db location from file
                BufferedReader readDir = new BufferedReader(new FileReader(userPrefs));
                dbLocation = readDir.readLine();
                lang = readDir.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Check if db exists
            File db = new File(dbLocation);
            if(db.exists()){
                //db exists
                System.out.println("Database trovato!");
                filePosition = dbLocation;
                return true;
            }
            else{
                System.err.println("Database non trovato!");
                return false;
            }
        }
        else{
            System.err.println("File di preferenze non trovato");
            return false;
        }
    }

    //Method that creates a new database file
    @Override
    public boolean createDatabaseFile(File db){
        //Get old preferences file
        userPrefs = new File(fileUserPreferences);
        //Deletes old file
        userPrefs.delete();
        try {
            //Creates new file in the same directory as old one
            if(userPrefs.createNewFile()){
                //User Prefernces file created
                if(db.createNewFile()){
                    //db file created, write location to user file
                    FileWriter fw = new FileWriter(userPrefs);
                    fw.write(db.getAbsolutePath());
                    filePosition = db.getAbsolutePath();
                    //writes initial language preference
                    fw.write("\n"+lang);
                    fw.flush();
                    fw.close();
                    System.out.println("File di preferenze e Database creati!");
                    return true;
                }else {
                    System.out.println("Database non creato");
                    return false;
                }
            } else {
                System.out.println("File di preferenze non creato!");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * Getters
     */
    @Override
    public String getLang() {
        return lang;
    }

    /*
     * Setters
     */
    @Override
    public void setLang(String lang) {
        try {
            //Changes language line in the preferences file
            Scanner sc = new Scanner(new File(fileUserPreferences));
            StringBuffer buffer = new StringBuffer();
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            //Added the backslash to check only the second line
            fileContents = fileContents.replaceAll("\n"+this.lang, "\n"+lang);
            FileWriter writer = new FileWriter(fileUserPreferences);
            writer.append(fileContents);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.lang = lang;
    }
}
