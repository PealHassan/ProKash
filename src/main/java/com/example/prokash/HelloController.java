package com.example.prokash;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
    private  TextField firstname;

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
    private Label verificationcodelabel,verificationcodeemaillabel,accountidinformationlabel,accountidinformationemaillabel;
    @FXML
    private JFXButton verificationcodebutton;
    @FXML
    private TextField verificationcodetextfield;
    @FXML
    private Label verifylabel, verifydonelabel;
    @FXML
    private ImageView doneimage;
    @FXML
    private ProgressIndicator loading;
    @FXML
    private JFXButton gotologinpagebutton;

    @FXML
    private Pane signuploadingpane, verificationcodepane;
    private final DatabaseFacilites databaseWork = new DatabaseFacilites();
    private static int AccountId = 100000;
    private String verificationcode;

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
    public void save() throws Exception {
        //Data Validation
        Validation.valid(firstname, lastname, mothername, fathername, email,occupation, postoffice, city, district, nationality, phonenumber, postalcode, nid, password, confirmpassword, religion, maritalstatus, gender, dateofbirth, income, AnchorPaneSignUpForm1);
        if(Validation.validationFlag == 0) {
            Validation.validationFlag = 1;
            return;
        }
        verificationcodepane.setVisible(true);

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
    public void SendVerificationCode() throws Exception {
        int code = (int)(Math.random()*1000000);
        verificationcode = String.valueOf(code);
        while(verificationcode.length()<6) verificationcode+="0";
        verificationcodelabel.setVisible(true);
        verificationcodeemaillabel.setVisible(true);
        verificationcodeemaillabel.setText(email.getText());
        verificationcodebutton.setText("Verify Account");
        verificationcodebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    VerifyAccount();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        MailFacilities.sendVerificationCode(verificationcode,email.getText());
    }
    public void VerifyAccount() throws SQLException {

        if(verificationcodetextfield.getText().equals(verificationcode)) {
            //Account Id Generate
            AccountId += (databaseWork.NumberOfUsers()+1);
            //Creating Map of data
            Map<String,String> data = this.CreateMap();
            //Insertion in database
            databaseWork.Insert(data);

            verificationcodepane.setVisible(false);
            signuploadingpane.setVisible(true);
            accountidinformationemaillabel.setText(data.get("Email"));
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(0);
                        accountidinformationemaillabel.setVisible(true);
                        accountidinformationlabel.setVisible(true);
                        loading.setVisible(false);
                        doneimage.setVisible(true);
                        verifylabel.setVisible(false);
                        verifydonelabel.setVisible(true);
                        AnchorPaneSignUpForm1.setVisible(false);
                        gotologinpagebutton.setVisible(true);
                        MailFacilities.sendAccountId(String.valueOf(AccountId),firstname.getText()+" "+lastname.getText(),email.getText());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    timer.cancel();
                }
            };
            timer.schedule(task,5000);
        }
        else {
            verificationcodebutton.setText("Send Me Verification Code");
            showingError.changeStyleError(verificationcodetextfield);
            verificationcodebutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        SendVerificationCode();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            verificationcodelabel.setVisible(false);
            verificationcodeemaillabel.setVisible(false);
        }

    }
    public void Refresh() {
        gotologinpagebutton.setVisible(false);
        verifydonelabel.setVisible(false);
        verifylabel.setVisible(true);
        doneimage.setVisible(false);
        loading.setVisible(true);
        showingError.changeStyleCorrect(verificationcodetextfield);
        verificationcodetextfield.setText("");
        verificationcodelabel.setVisible(false);
        verificationcodebutton.setText("Send Me Verification Code");
        verificationcodebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    SendVerificationCode();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        verificationcodeemaillabel.setVisible(false);
        signuploadingpane.setVisible(false);
        verificationcodepane.setVisible(false);
        AnchorPaneSignUpForm2.setVisible(false);
        AnchorPaneSignUpForm1.setVisible(false);
        accountidinformationemaillabel.setVisible(false);
        accountidinformationlabel.setVisible(false);
        showingError.Refresh(firstname, lastname, mothername, fathername, email,occupation, postoffice, city, district, nationality, phonenumber, postalcode, nid, password, confirmpassword, religion, maritalstatus, gender, dateofbirth, income, AnchorPaneSignUpForm1);

    }

}