package com.example.prokash;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    AnchorPane AnchorPaneSignUpForm1, AnchorPaneSignUpForm2;
    @FXML
    private TextField city;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private DatePicker dateofbirth;

    @FXML
    private TextField district;

    @FXML
    private TextField email;

    @FXML
    private TextField fathername;

    @FXML
    private TextField firstname;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private ComboBox<String> income;

    @FXML
    private TextField lastname;

    @FXML
    private ComboBox<String> maritalstatus;

    @FXML
    private TextField mothername;

    @FXML
    private TextField nationality;

    @FXML
    private TextField nid;

    @FXML
    private TextField occupation;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phonenumber;

    @FXML
    private TextField postalcode;

    @FXML
    private TextField postoffice;

    @FXML
    private ComboBox<String> religion;

    @FXML
    private Pane signuploadingpane;
    private final DatabaseFacilites databaseWork = new DatabaseFacilites();
    private int AccountId = 100000;

    public void SignUp() {
        AnchorPaneSignUpForm1.setVisible(true);
    }
    public void NextToSignUpForm1ToSignUpForm2() {
        AnchorPaneSignUpForm1.setVisible(false);
        AnchorPaneSignUpForm2.setVisible(true);
    }
    public void BackFromSignUpForm1ToLogIn() {
        AnchorPaneSignUpForm1.setVisible(false);
    }
    public void BackFromSignUpForm2ToSignUpForm1() {
        AnchorPaneSignUpForm2.setVisible(false);
        AnchorPaneSignUpForm1.setVisible(true);
    }
    public void save() throws SQLException{
       // Data Validation
        Validation.valid(firstname, lastname, mothername, fathername, occupation, postoffice, city, district, nationality, phonenumber, postalcode, nid, password, confirmpassword, religion, maritalstatus, gender, dateofbirth, income, AnchorPaneSignUpForm1);
        if(Validation.validationFlag == 0) {
            Validation.validationFlag = 1;
            return;
        }
        //Account Id Generate
        AccountId += (databaseWork.NumberOfUsers()+1);
        //Creating Map of data
        Map<String,String> data = this.CreateMap();
        //Insertion in database
        databaseWork.Insert(data);
        signuploadingpane.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                signuploadingpane.setVisible(false);
                AnchorPaneSignUpForm1.setVisible(false);
                AnchorPaneSignUpForm2.setVisible(false);
            }
        };
        timer.schedule(task,3000);
//        Thread thread = new Thread();
//        thread.start();

//        while(thread.isAlive()) {
//            signuploadingpane.setVisible(true);
//        }
//        AnchorPaneSignUpForm1.setVisible(false);
//        AnchorPaneSignUpForm2.setVisible(false);
//        signuploadingpane.setVisible(false);
    }



    public Map<String,String> CreateMap() {
        Map<String,String> tempdata = new HashMap<String, String>();
        tempdata.put("AccountId",String.valueOf(AccountId));
        tempdata.put("FirstName",firstname.getText());
        tempdata.put("LastName",lastname.getText());
        tempdata.put("MotherName",mothername.getText());
        tempdata.put("FatherName",fathername.getText());
        tempdata.put("Email",email.getText());
        tempdata.put("Nationality",nationality.getText());
        tempdata.put("Religion",religion.getValue());
        tempdata.put("PhoneNumber",phonenumber.getText());
        tempdata.put("MaritalStatus",maritalstatus.getValue());
        tempdata.put("Gender",gender.getValue());
        tempdata.put("DataOfBirth", String.valueOf(dateofbirth.getValue()));
        tempdata.put("PostOffice",postoffice.getText());
        tempdata.put("City",city.getText());
        tempdata.put("District",district.getText());
        tempdata.put("PostalCode",postalcode.getText());
        tempdata.put("NID",nid.getText());
        tempdata.put("Income",income.getValue());
        tempdata.put("Occupation",occupation.getText());
        tempdata.put("Password",password.getText());
        tempdata.put("ConfirmPassword", confirmpassword.getText());
        return  tempdata;
    }

}