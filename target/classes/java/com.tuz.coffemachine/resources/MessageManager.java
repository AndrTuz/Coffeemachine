package com.tuz.coffemachine.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private ResourceBundle resourceBundle;

    public MessageManager(){
    }
    public String getProperty(String key,String lang){
        Locale locale = new Locale(lang, "US");
        resourceBundle = ResourceBundle.getBundle("resources.messages", locale);
        return resourceBundle.getString(key);
    }

}
