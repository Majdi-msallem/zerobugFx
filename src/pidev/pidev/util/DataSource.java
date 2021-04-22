/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.pidev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author majdi
 */
public class DataSource {
private static DataSource instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/last";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    public DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connecting to DB  !");
        } catch (SQLException ex) {
            System.out.println("pas de connexion !!!");
        }
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
