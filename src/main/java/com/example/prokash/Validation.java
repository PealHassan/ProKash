package com.example.prokash;


import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Validation {
    static int validationFlag = 1;
    static boolean checkDepositAmount(String s1) {

        int n = s1.length();
        if(n == 0) return false;
        for(int i = 0; i<n; i++)
            if(!(s1.charAt(i)>='0' && s1.charAt(i)<='9')) return  false;
        if(Integer.valueOf(s1) <= 0) return  false;
        return  true;
    }

    static boolean CheckAllCharacterWithSpace(String s1) {
        int n= s1.length();
        if(n == 0) return false;
        for(int i=0;i<n;i++) {
            char a = s1.charAt(i);
            if(! ((a >= 'a' &&  a <= 'z') || (a >= 'A'  &&  a <= 'Z') || a == ' ') ) return  false;
        }
        return true;
    }

    static boolean CheckAllCharacterWithoutSpace(String s1) {
        int n= s1.length();
        if(n == 0) return false;
        for(int i=0;i<n;i++) {
            char a = s1.charAt(i);
            if(! ((a >= 'a' &&  a <= 'z') || (a >= 'A'  &&  a <= 'Z')) ) return  false;
        }
        return true;
    }
    static boolean CheckPin(String s1) {
        int n= s1.length();
        if(n < 6) return false;
        for(int i=0;i<n;i++) {
            char a = s1.charAt(i);
            if(! (a >= '0' &&  a <= '9') ) return  false;
        }
        return true;
    }

    static boolean CheckTwoStringEqual(String s1,String s2) {
        if(s1.length()<6)
            return false;
        return s1.equals(s2);
    }

    static boolean CheckValidPhoneNumber(String s1) {
        int n=s1.length();
        if(n!=10)
            return false;
        if(s1.charAt(0)!='1')
            return false;

        for(int i=0;i<n;i++) {
            char ch1=s1.charAt(i);
            if(!(ch1 >= '0' && ch1 <= '9'))  return false;
        }
        return true;
    }

    static boolean CheckValidPostalCode(String s1) {
        int n=s1.length();
        if(n!=4)
            return false;
        for(int i=0;i<n;i++) {
            char ch1=s1.charAt(i);
            if(!(ch1 >= '0' && ch1 <= '9')) return false;
        }
        return true;
    }
    static boolean checkValidAccountId(String s1) {
        int n = s1.length();
        if(n!=6) return false;
        for(int i = 0; i<n; i++) {
            char a = s1.charAt(i);
            if(!(a>='0'&& a<='9')) return false;
        }
        return true;
    }
    static boolean CheckValidNid(String s1) {
        int n= s1.length();
        if(n <= 8) return  false;
        for(int i=0;i<n;i++) {
            char a = s1.charAt(i);
            if(!(a>='0' &&  a<='9')) return  false;
        }
        return true;
    }
    static void valid(TextField firstname, TextField lastname, TextField mothername, TextField fathername, TextField email, TextField occupation, TextField postoffice, TextField city, TextField district, TextField nationality, TextField phonenumber, TextField postalcode, TextField nid, TextField password, TextField confirmpassword, ComboBox<String> religion, ComboBox<String> maritalstatus, ComboBox<String> gender, DatePicker dateofbirth, ComboBox<String> income, AnchorPane AnchorPaneSignUpForm1) {

    }
    public static void main(String[] args) throws SQLException {
//        Double a = 5.11111111111111;
//        DecimalFormat df = new DecimalFormat("#.##");
//        a = Double.valueOf(df.format(a));
//        System.out.println(df.format(a));
//        System.out.println(a);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(String.valueOf(dtf.format(now)));
//
//        Map<String,Double> map = new HashMap<String,Double>();
//
//        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
//        list.sort(Map.Entry.comparingByValue());
//        Map<String, Double> result = new LinkedHashMap<>();
//        for (Map.Entry<String, Double> entry : list) {
//            result.put(entry.getKey(), entry.getValue());
//        }
//
//
//        for(String i: result.keySet()) {
//            System.out.println(i + " " + result.get(i));
//        }
//        Map<String,Double> map = new HashMap<String,Double>();
//        map.put("kul",30.0);
//        map.put("kuj",40.0);
//        for(String i: map.keySet()) {
//            map.put(i,10.0 + map.get(i));
//        }
//        for(String i: map.keySet()) {
//            System.out.println(i + " " + map.get(i));
//        }
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(Integer.valueOf(String.valueOf(dtf.format(now))));
//        String date = "Ihsan 9mirani";
//        System.out.println(date.toLowerCase());
//        String query = "select * from Demo.UserInformation where AccountId = '100001'";
//        Statement statement = new DatabaseFacilites().getConnection().createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//        resultSet.next();
//        System.out.println(resultSet.getString("FirstName"));
        System.out.println(-4%3);
    }


}