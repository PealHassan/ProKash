package com.example.prokash;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private TextField state;
    private final DatabaseFacilites databaseWork = new DatabaseFacilites();
    Map<String,String> data = new HashMap<String, String>();

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
    public void save()  {
          data = this.CreateMap();
          Validation validation = new Validation();
          if(validation.CheckAllChatecterWithSpace(firstname.getText()))

          databaseWork.Insert(data);
    }
    public Map<String,String> CreateMap() {
        Map<String,String> tempdata = new HashMap<String, String>();
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
        tempdata.put("State",state.getText());
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