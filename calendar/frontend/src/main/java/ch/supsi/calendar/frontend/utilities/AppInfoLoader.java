package ch.supsi.calendar.frontend.utilities;

import java.io.IOException;
import java.util.Properties;

public class AppInfoLoader {
    //loads the properties contained in the pom.xml
    public static Properties getAppInfo(){
        final Properties properties=new Properties();
        try{
            properties.load(AppInfoLoader.class.getClassLoader().getResourceAsStream("calendar.properties"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return properties;
    }
}