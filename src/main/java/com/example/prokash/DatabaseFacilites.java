package com.example.prokash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

public class DatabaseFacilites {
    public Connection databaselink;


    public Connection getConnection() {
        String databaseName = "Customer";
        String databaseUser = "root";
        String databasePassword = "kvzoszhhzmP45?";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            System.out.println("sorry");
        }
        return databaselink;
    }
    public void Insert(Map<String,String>data) {
        Connection connectionDatabase = this.getConnection();

        String query = "INSERT INTO Demo.UserInformation (";
        int cnt = 0;
        for(Map.Entry<String,String> e: data.entrySet()) {
            if(cnt == 0) query += e.getKey();
            else query += (", " + e.getKey());
            cnt++;
        }
        query += ") VALUES (";
        cnt = 0;
        for(Map.Entry<String,String> e: data.entrySet()) {
            if(cnt == 0) query += ("'"+e.getValue()+"'");
            else query += (", '" + e.getValue()+"'");
            cnt++;
        }
        query += ")";
        try {
            Statement statement = connectionDatabase.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
