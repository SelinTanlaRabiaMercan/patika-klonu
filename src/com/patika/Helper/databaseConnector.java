package com.patika.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnector {
    private Connection connection=null;
    public Connection connectdb(){
        try {
            this.connection= DriverManager.getConnection(configSabitler.DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.connection;
    }
    public static Connection getInstance(){
        databaseConnector databaseconnector=new databaseConnector();
        return databaseconnector.connectdb();
    }
}
