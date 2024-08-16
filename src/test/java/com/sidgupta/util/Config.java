package com.sidgupta.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize(){

        properties = getProperties();

        //Check for Override
        for(String key : properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key))
                properties.setProperty(key,System.getProperty(key));
        }

        //print on console
        log.info("Test Properties");
        log.info("-----------------------");
        for(String key : properties.stringPropertyNames())
            log.info("{}={}",key,properties.getProperty(key));
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    private static Properties getProperties(){

        Properties properties = new Properties();

        try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            properties.load(stream);
        }
        catch(Exception e){
            log.error("Cannot read properties file located at : {}",DEFAULT_PROPERTIES,e);
        }

        return properties;
    }

}
