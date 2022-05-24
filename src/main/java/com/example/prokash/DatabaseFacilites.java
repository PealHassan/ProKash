package com.example.prokash;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseFacilites {
    public Connection databaselink = this.getConnection();


    public Connection getConnection() {
        String databaseName = "Customer";
        String databaseUser = "root";
        String databasePassword = "peal?hassan?";
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
    public void transactionInsert(String UserAccountId, String transactiontype, String amount, String Charge, String toAccountId, String toUsername,String date) {

        String query = "INSERT INTO Demo.R" + UserAccountId + " ( " + "TransactionType" + ", " + "AccountId" + ", " + "UserName" + ", " + "Amount" + ", " + "Charge" + "," + "Date" + " ) VALUES ( " + "'" + transactiontype + "'" + ", " + "'" + toAccountId + "'" + ", " + "'" + toUsername + "'"+ ", " + "'" + amount + "'" + ", " + "'" + Charge + "'" + "," + "'" + date + "'" + " )";

        try {
            Statement statement = databaselink.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int NumberOfUsers() throws SQLException {
        String query = "SELECT COUNT(AccountId) From Demo.UserInformation";
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int totaldata = 0;
        while(resultSet.next()) totaldata = resultSet.getInt(1);
        return totaldata;
    }
    public boolean UserExist(String column,String Value) throws SQLException {
        String query = "SELECT * FROM Demo.UserInformation WHERE " + column + " = " + Value;
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) return true;
        return false;
    }
    public boolean isMatch(String column1, String value1, String column2, String value2) throws SQLException {
        String query = "SELECT * FROM Demo.UserInformation WHERE " + column1 + " = " + value1;
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) if(resultSet.getString(column2).equals(value2)) return true;
        return false;
    }
    public boolean forgetPasswordVerification(String accountid, String nid, String phonenumber) throws SQLException {
        String query = "SELECT * FROM Demo.UserInformation WHERE AccountId" + " = " + accountid;
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            if(resultSet.getString("NID").equals(nid) && resultSet.getString("PhoneNumber").equals(phonenumber)) return true;
        }
        return false;
    }
    public String GetInformation(String accountid, String information) throws SQLException {
        String query = "SELECT * FROM Demo.UserInformation WHERE AccountId" + " = " + accountid;
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) return resultSet.getString(information);
        return null;
    }
    public void Update(String column, String value, String primarykey) throws SQLException {
        String query = "UPDATE Demo.UserInformation SET " + column + " = \"" + value + "\" WHERE AccountId = " + primarykey;
        Statement statement = databaselink.createStatement();
        statement.executeUpdate(query);
    }

    public void createTransactionsTable(String AccoundId) throws SQLException {
        String query = "CREATE TABLE Demo.R" + AccoundId + " ( TransactionType varChar(255), AccountId varChar(255), UserName varChar(255), Amount varChar(255), Charge varChar(255) , Date varChar(255) )";
        Statement statement = databaselink.createStatement();
        statement.executeUpdate(query);
    }
    public int TotalTransaction(String UserAccountId) throws SQLException {
        String query = "SELECT COUNT(AccountId) From Demo.R" + UserAccountId;
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int totaldata = 0;
        while(resultSet.next()) totaldata = resultSet.getInt(1);
        return totaldata;
    }
    public Map<String,Double> GetInformationTransaction(String startdate, String lastdate, String subjectOn, String gendertype) throws SQLException {
        //System.out.println("hello");

        String query = "select * from Demo.UserInformation";
        Statement statement = databaselink.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Map<String,Double> map = new HashMap<String,Double>();
        while(resultSet.next()) {
            if(!resultSet.getString("Gender").equals(gendertype)) continue;
            String str = resultSet.getString("AccountId");
            String query2 = "select * from Demo.R"+str;
            Statement statement2 = databaselink.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(query2);

            while(resultSet2.next()) {
                String str2 = resultSet2.getString("Date");
                if(str2.compareTo(startdate)>=0 && str2.compareTo(lastdate)<=0 && subjectOn.equals("Reserved Money")) {
                    Double val = Double.valueOf(resultSet2.getString("Charge"));
                    if(resultSet2.getString("TransactionType").equals("Deposit")) {
                        val += Double.valueOf(resultSet2.getString("Amount"));
                    }
                    else if(resultSet2.getString("TransactionType").equals("Withdraw")) {
                        val -= Double.valueOf(resultSet2.getString("Amount"));
                    }
                    Double val2 = map.get(str2);
                    if(val2 == null) val2 = 0.0;
                    map.put(str2,val+val2);
                }
                else if(str2.compareTo(startdate)>=0 && str2.compareTo(lastdate)<=0 && resultSet2.getString("TransactionType").equals(subjectOn)) {
                    Double val = Double.valueOf(resultSet2.getString("Amount"));
                    Double val2 = map.get(str2);
                    if(val2 == null) val2 = 0.0;
                    map.put(str2,val + val2);
                }
            }
        }
        return map;
    }


}
