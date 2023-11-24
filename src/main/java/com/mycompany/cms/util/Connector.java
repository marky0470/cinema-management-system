/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms.util;

import com.mycompany.cms.util.ConfigReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marks
 */
public class Connector {
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbUrl = ConfigReader.getProperty("database.url");
            String dbUsername = ConfigReader.getProperty("database.username");
            String dbPassword = ConfigReader.getProperty("database.password");

            Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
