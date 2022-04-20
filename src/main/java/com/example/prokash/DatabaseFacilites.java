package com.example.prokash;

import java.sql.*;
import java.util.Map;

public class DatabaseFacilites {
    public Connection databaselink = this.getConnection();


    public Connection getConnection() {
        String databaseName = "Customer";
        String databaseUser = "root";
        String databasePassword = "database?motherchod";
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
            Statement statement = databaselink.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int NumberOfUsers() throws SQLException {
        String query = "SELECT * FROM Demo.UserInformation";
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int totaldata = 0;
        while(resultSet.next()) totaldata++;
        return totaldata;
    }
}
