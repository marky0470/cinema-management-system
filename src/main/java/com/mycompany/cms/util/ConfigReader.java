/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.cms.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author marks
 */
public class ConfigReader {
    private static final Properties properties = new Properties();
    
    static {
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
