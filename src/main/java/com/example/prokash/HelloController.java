package com.example.prokash;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.Animation;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.prokash.Validation.*;

public class HelloController  implements Initializable{
    @FXML
    private AnchorPane AnchorPaneForgetPassword;
    @FXML
    private TextField UserPassword, UserAccountId;
    @FXML
    private Label Errorlabel, accountiderrorlabel;
    @FXML
    private ImageView crosssignimage;
    @FXML
    private Pane ErrorDialogPane;
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
    private ProgressIndicator loading,verificationcodeprogressindicator;
    @FXML
    private JFXButton gotologinpagebutton;

    @FXML
    private Label currentbalancelabel;

    @FXML
    private Pane signuploadingpane, verificationcodepane;

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
    int validationFlag = 1;
    public void save() throws Exception {
        //Data Validation
        //Validation.valid(firstname, lastname, mothername, fathername, email,occupation, postoffice, city, district, nationality, phonenumber, postalcode, nid, password, confirmpassword, religion, maritalstatus, gender, dateofbirth, income, AnchorPaneSignUpForm1);
        if(!CheckAllCharacterWithSpace(firstname.getText())) {
            validationFlag=0;
            showingError.changeStyleError(firstname);
            signfirstnamerr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(firstname);
            signfirstnamerr.setVisible(false);
        }

        if(!CheckAllCharacterWithSpace(lastname.getText())) {
            validationFlag=0;
            showingError.changeStyleError(lastname);
            signlasterr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(lastname);
            signlasterr.setVisible(false);
        }

        if(!CheckAllCharacterWithSpace(mothername.getText())) {
            validationFlag=0;
            showingError.changeStyleError(mothername);
            signmothererr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(mothername);
            signmothererr.setVisible(false);
        }

        if(!CheckAllCharacterWithSpace(fathername.getText())) {
            validationFlag=0;
            showingError.changeStyleError(fathername);
            signfathererr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(fathername);
            signfathererr.setVisible(false);
        }



        if(!CheckAllCharacterWithoutSpace(postoffice.getText())) {
            validationFlag=0;
            showingError.changeStyleError(postoffice);
            signposterr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(postoffice);
            signposterr.setVisible(false);
        }

        if(!CheckAllCharacterWithoutSpace(city.getText())) {
            validationFlag=0;
            showingError.changeStyleError(city);
            signcityerr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(city);
            signcityerr.setVisible(false);
        }

        if(!CheckAllCharacterWithoutSpace(district.getText())) {
            validationFlag=0;
            showingError.changeStyleError(district);
            signdistricterr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(district);
            signdistricterr.setVisible(false);
        }

        if(!CheckAllCharacterWithoutSpace(nationality.getText())) {
            validationFlag=0;
            showingError.changeStyleError(nationality);
            signnationalityerr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(nationality);
            signnationalityerr.setVisible(false);
        }

        if(!CheckValidPhoneNumber(phonenumber.getText())) {
            validationFlag=0;
            showingError.changeStyleError(phonenumber);
            signphonenumbererr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(phonenumber);
            signphonenumbererr.setVisible(false);
        }

        if(!CheckValidPostalCode(postalcode.getText())) {
            validationFlag=0;
            showingError.changeStyleError(postalcode);
        }
        else showingError.changeStyleCorrect(postalcode);

        if(!CheckAllCharacterWithSpace(occupation.getText())) {
            validationFlag=0;
            showingError.changeStyleError(occupation);
            signoccupationerr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(occupation);
            signoccupationerr.setVisible(false);
        }

        if(!CheckValidNid(nid.getText())) {
            validationFlag=0;
            showingError.changeStyleError(nid);
            signniderr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(nid);
            signniderr.setVisible(false);
        }
        if(!CheckTwoStringEqual(password.getText(), confirmpassword.getText())) {
            validationFlag=0;
            showingError.changeStyleError(password);
            showingError.changeStyleError(confirmpassword);
            signpassworderr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(password);
            showingError.changeStyleCorrect(confirmpassword);
            signpassworderr.setVisible(false);
        }

        if(religion.getValue()==null) {
            validationFlag=0;
            showingError.changeStyleError(religion);
            signreligionerr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(religion);
            signreligionerr.setVisible(false);
        }

        if(maritalstatus.getValue()==null) {
            validationFlag=0;
            showingError.changeStyleError(maritalstatus);
            signmaritalstatuserr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(maritalstatus);
            signmaritalstatuserr.setVisible(false);
        }

        if(gender.getValue()==null) {
            validationFlag=0;
            showingError.changeStyleError(gender);
            signgendererr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(gender);
            signgendererr.setVisible(false);
        }

        if(dateofbirth.getValue()==null) {
            validationFlag=0;
            showingError.changeStyleError(dateofbirth);
            signbirtherr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(dateofbirth);
            signbirtherr.setVisible(false);
        }

        if(income.getValue()==null) {
            validationFlag=0;
            showingError.changeStyleError(income);
            signincomeerr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(income);
            signincomeerr.setVisible(false);
        }


        if(!MailFacilities.isAddressValid(email.getText())) {
            validationFlag = 0;
            showingError.changeStyleError(email);
            signemailerr.setVisible(true);
        }
        else {
            showingError.changeStyleCorrect(email);
            signemailerr.setVisible(false);
        }

        if(validationFlag == 0) {
            validationFlag = 1;
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
        tempdata.put("DateOfBirth", String.valueOf(dateofbirth.getValue()));
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
        verificationcodeprogressindicator.setVisible(true);
        verificationcodebutton.setText("Verify Account");
        verificationcodebutton.setVisible(false);
        int code = (int)(Math.random()*1000000);
        verificationcode = String.valueOf(code);
        while(verificationcode.length()<6) verificationcode+="0";

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                verificationcodeprogressindicator.setVisible(false);
                verificationcodelabel.setVisible(true);
                verificationcodeemaillabel.setVisible(true);
                //verificationcodeemaillabel.setText(email.getText());
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
                verificationcodebutton.setVisible(true);
                try {
                    MailFacilities.sendVerificationCode(verificationcode,email.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule(task,5000);
    }
    public void VerifyAccount() throws SQLException {

        if(verificationcodetextfield.getText().equals(verificationcode)) {
            DatabaseFacilites databaseWork = new DatabaseFacilites();
            //Account Id Generate
            AccountId =100000 + (databaseWork.NumberOfUsers("Demo.UserInformation")+1);

            databaseWork.createTransactionsTable(String.valueOf(AccountId));
            //Creating Map of data
            Map<String,String> data = this.CreateMap();
            //Insertion in database
            databaseWork.Insert(data,"Demo.UserInformation");


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
    @FXML
    private AnchorPane AnchorPaneUser;
    @FXML
    private Pane profilepane,homepane;
    public void login() throws SQLException {
        int accountidvalid = 0, passwordvalid = 0;
        if(UserAccountId.getText().equals("") || !Validation.checkValidAccountId(UserAccountId.getText())) showingError.changeStyleError(UserAccountId);
        else {
            accountidvalid = 1;
            showingError.changeStyleCorrect(UserAccountId);
        }
        if(UserPassword.getText().equals("")) showingError.changeStyleError(UserPassword);
        else {
            passwordvalid = 1;
            showingError.changeStyleCorrect(UserPassword);
        }
        if(accountidvalid == 0 || passwordvalid == 0) return;
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        if(!databaseWork.UserExist("AccountId",UserAccountId.getText(),"demo.userInformation")) {
            ErrorDialogPane.setVisible(true);
            accountiderrorlabel.setText("Accout Id doesn't exist");
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    ErrorDialogPane.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        if(!databaseWork.isMatch("AccountId", UserAccountId.getText(),"Password",UserPassword.getText())) {
            ErrorDialogPane.setVisible(true);
            accountiderrorlabel.setText("Password doesn't match");
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    ErrorDialogPane.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        AnchorPaneUser.setVisible(true);
        String temp = databaseWork.GetInformation(UserAccountId.getText(),"CurrentBalance");
        if(temp == null) temp = "0";
        currentbalancelabel.setText(temp + " BDT");
        loginusernamelabel.setText(databaseWork.GetInformation(UserAccountId.getText(), "FirstName") + " " + databaseWork.GetInformation(UserAccountId.getText(), "LastName"));
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("Select * from demo.themeinformation where AccountId = '%s'",UserAccountId.getText());
        ResultSet res = st.executeQuery(query);
        if(!res.next()) {
            String query2 = String.format("insert into demo.themeinformation (AccountId, Theme) values ('%s','%s')",UserAccountId.getText(),"Theme1.css");
            Statement st2 = new DatabaseFacilites().getConnection().createStatement();
            st2.executeUpdate(query2);
        }
        res = st.executeQuery(query);
        res.next();
        temptheme = res.getString("Theme");
        refreshTheme();

        VisibilityUpdate(homepane);


    }
    @FXML
    private Pane pane1,pane2;
    String temptheme = "Theme1.css";
    public void changeTheme() throws SQLException {
        //System.out.println(temp);

        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("Select * from demo.themeinformation where AccountId = '%s'",UserAccountId.getText());
        ResultSet res = st.executeQuery(query);
        if(!res.next()) {
            String query2 = String.format("insert into demo.themeinformation (AccountId, Theme) values ('%s','%s')",UserAccountId.getText(),temptheme);
            Statement st2 = new DatabaseFacilites().getConnection().createStatement();
            st2.executeUpdate(query2);
        }
        else {
            String query2 = String.format("update demo.themeinformation set Theme = '%s' where AccountId = '%s'",temptheme,UserAccountId.getText());
            Statement st2 = new DatabaseFacilites().getConnection().createStatement();
            st2.executeUpdate(query2);
        }
        //refreshTheme();
    }
    @FXML
    private Label signbirtherr;

    @FXML
    private Label signcityerr;

    @FXML
    private Label signconfirmpassworderr;

    @FXML
    private Label signdistricterr;

    @FXML
    private Label signemailerr;

    @FXML
    private Label signfathererr;

    @FXML
    private Label signfirstnamerr;

    @FXML
    private Label signgendererr;

    @FXML
    private Label signincomeerr;

    @FXML
    private Label signlasterr;

    @FXML
    private Label signmaritalstatuserr;

    @FXML
    private Label signmothererr;

    @FXML
    private Label signnationalityerr;

    @FXML
    private Label signniderr;

    @FXML
    private Label signoccupationerr;

    @FXML
    private Label signpassworderr;

    @FXML
    private Label signphonenumbererr;

    @FXML
    private Label signpostalerr;

    @FXML
    private Label signposterr;

    @FXML
    private Label signreligionerr;

    public void inTheme1() {
        pane1.setStyle("-fx-background-color: green; -fx-border-color: black");
        pane2.setStyle("-fx-background-color: white; -fx-border-color: black");
        temptheme = "Theme1.css";
        //AnchorPaneUser.getStylesheets().add(getClass().getResource("Theme1.css").toExternalForm());
    }
    public void inTheme2() {
        pane1.setStyle("-fx-background-color: white; -fx-border-color: black");
        pane2.setStyle("-fx-background-color: green; -fx-border-color: black");
        temptheme = "Theme2.css";
       // AnchorPaneUser.getStylesheets().add(getClass().getResource("Theme2.css").toExternalForm());
    }
    public void ForgetPassword() {
        AnchorPaneForgetPassword.setVisible(true);
    }
    @FXML
    private  TextField forgetaccountidverify, forgetnidverify, forgetphonenumberverify;
    @FXML
    private  Pane wronginformationpane,forgetpasswordloadingpane,forgetpasswordverificationcodepane;
    @FXML
    private ProgressIndicator forgetpasswordprogressindicator;
    @FXML
    private Label forgetpasswordverifyingyourinformationlabel;
    @FXML
    private Label loginusernamelabel, profileusernamelabel,profileemaillabel, profileincomelabel, profilegenderlabel, profiledateofbirthlabel, profiletotaltransactionslabel, profilecurrentbalancelabel;
    private String forgetpasswordverificationcodevalue;
    public void ForgetPasswordVerification() throws Exception {
        int flag = 0;
        if(!Validation.checkValidAccountId(forgetaccountidverify.getText())) {
            showingError.changeStyleError(forgetaccountidverify);
            flag = 1;
        }
        else showingError.changeStyleCorrect(forgetaccountidverify);
        if(!Validation.CheckValidNid(forgetnidverify.getText())) {
            showingError.changeStyleError(forgetnidverify);
            flag = 1;
        }
        else showingError.changeStyleCorrect(forgetnidverify);
        if(!CheckValidPhoneNumber(forgetphonenumberverify.getText())) {
            showingError.changeStyleError(forgetphonenumberverify);
            flag = 1;
        }
        else showingError.changeStyleCorrect(forgetphonenumberverify);
        if(flag == 1) return;
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        if(!databaseWork.forgetPasswordVerification(forgetaccountidverify.getText(),forgetnidverify.getText(),forgetphonenumberverify.getText())) {
            wronginformationpane.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    wronginformationpane.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;

        }
        forgetpasswordloadingpane.setVisible(true);
        String UserEmailId = databaseWork.GetInformation(forgetaccountidverify.getText(),"Email");
        int code = (int)(Math.random()*1000000);
        forgetpasswordverificationcodevalue = String.valueOf(code);
        while(forgetpasswordverificationcodevalue.length()<6) forgetpasswordverificationcodevalue+="0";
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                forgetpasswordprogressindicator.setVisible(false);
                forgetpasswordverifyingyourinformationlabel.setVisible(false);
                forgetpasswordverificationcodepane.setVisible(true);
                try {
                    MailFacilities.sendVerificationCode(forgetpasswordverificationcodevalue,UserEmailId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                timer.cancel();
            }
        };
        timer.schedule(task,5000);
    }
    @FXML
    private TextField forgetpasswordverificationcodetextfield;
    @FXML
    private Pane forgetPasswordSetPasswordPane,successfullypasswordupdatedpane;
    @FXML
    private  Pane bankingpane;
    @FXML
    private TextField forgetsetpassword,forgetsetconfirmpassword;
    @FXML
    private JFXButton backtologinpage;
    public void forgetPasswordVerifyCode() {
        if(!forgetpasswordverificationcodevalue.equals(forgetpasswordverificationcodetextfield.getText())) {
            showingError.changeStyleError(forgetpasswordverificationcodetextfield);
            return;
        }
        else showingError.changeStyleCorrect(forgetpasswordverificationcodetextfield);
        forgetpasswordverificationcodepane.setVisible(false);
        forgetPasswordSetPasswordPane.setVisible(true);
    }
    public void updatePassword() throws SQLException {
        if(!Validation.CheckTwoStringEqual(forgetsetpassword.getText(),forgetsetconfirmpassword.getText()) || forgetsetpassword.getText().length()<6) {
            showingError.changeStyleError(forgetsetpassword);
            showingError.changeStyleError(forgetsetconfirmpassword);
            return;
        }
        else {
            showingError.changeStyleCorrect(forgetsetpassword);
            showingError.changeStyleCorrect(forgetsetconfirmpassword);
        }
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        databaseWork.Update("Password", forgetsetpassword.getText(),forgetaccountidverify.getText());
        databaseWork.Update("ConfirmPassword", forgetsetconfirmpassword.getText(),forgetaccountidverify.getText());
        backtologinpage.setVisible(true);
        successfullypasswordupdatedpane.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                successfullypasswordupdatedpane.setVisible(false);
                timer.cancel();
            }
        };
        timer.schedule(task,3000);
        return;
    }
    public void BackToLogInPage() {
        backtologinpage.setVisible(false);
        forgetPasswordSetPasswordPane.setVisible(false);
        forgetpasswordprogressindicator.setVisible(true);
        forgetpasswordverifyingyourinformationlabel.setVisible(true);
        forgetpasswordloadingpane.setVisible(false);
        AnchorPaneForgetPassword.setVisible(false);
        forgetaccountidverify.setText("");
        forgetphonenumberverify.setText("");
        forgetnidverify.setText("");
        forgetpasswordverificationcodetextfield.setText("");
    }
    public void HomeOnMouseClicked() {

        VisibilityUpdate(homepane);
    }
    public void ProfileOnMouseClicked() throws SQLException {
        makeProfile(UserAccountId.getText(),0);
    }
    @FXML
    private  Pane bankingdepositpane;
    @FXML
    private Label depositamountlabel;

    @FXML
    private Line depositamountline;

    @FXML
    private TextField depositamounttextfield;
    @FXML
    private Label depositaccountidlabel;
    @FXML
    private TextField depositaccountidtextfield;
    @FXML
    private Line depositaccountidline;

    @FXML
    private JFXButton depositpanebackbtn;
    @FXML
    private Pane depositsuccessmessage;
    @FXML
    private Pane depositerrormessage;
    @FXML
    private Pane agentmodebankingpane;

    @FXML
    private Pane agentmodepane,agenterrormessage,depositerrormessage1;
    @FXML
    private TextField agentid, agentpin;
    @FXML
    private Line agentidline, agentpinline;
    @FXML
    private  Label agentidlabel,agentpinlabel;
    @FXML
    private JFXButton withdraw;

    @FXML
    private Label withdrawaccountidlabel;

    @FXML
    private Line withdrawaccountidline;

    @FXML
    private TextField withdrawaccountidtextfield;

    @FXML
    private Label withdrawamountlabel;

    @FXML
    private Line withdrawamountline;

    @FXML
    private TextField withdrawamounttextfield;
    @FXML
    private Pane bankingwithdrawpane;

    @FXML
    private JFXButton withdrawbackbtn;

    @FXML
    private Pane withdrawerrormessage;

    @FXML
    private Pane withdrawerrormessage1,withdrawerrormessage2;

    @FXML
    private Pane withdrawsuccessmessage;

    public void BankingOnMouseClicked() {
        VisibilityUpdate(bankingpane);
    }

    public void logout() {

        AnchorPaneUser.setVisible(false);
        VisibilityUpdate(userpane);
        UserAccountId.setText("");
        UserPassword.setText("");
    }


    public void EnteringDepositPane() {
        EnteringDepositPaneRefresh();
        VisibilityUpdate(bankingdepositpane);
    }
    public void EnteringDepositPaneRefresh() {
        depositamounttextfield.setText("");
        depositamountlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        depositamountline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
        depositaccountidtextfield.setText("");
        depositaccountidlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        depositaccountidline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
    }
    public void BackFromEnteringDepositPane() throws SQLException {
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("select * from demo.agentinformation where AccountId = '%s'",agentid.getText());
        ResultSet res = st.executeQuery(query);
        res.next();
        agentbalance.setText("Balance : " + res.getString("Balance") + " BDT");
        VisibilityUpdate(agentmodebankingpane);
    }
    @FXML
    private Label limitcrossed;
    public void Deposit() throws SQLException {
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        if(!databaseWork.UserExist("AccountId",depositaccountidtextfield.getText(),"demo.userinformation")) {
            depositaccountidline.setStroke(Paint.valueOf("red"));
            depositaccountidlabel.setTextFill(Paint.valueOf("red"));
            depositerrormessage1.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    depositerrormessage1.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        else {
            depositaccountidline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
            depositaccountidlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        }
        if(!Validation.checkDepositAmount(depositamounttextfield.getText())) {
            depositamountline.setStroke(Paint.valueOf("red"));
            depositamountlabel.setTextFill(Paint.valueOf("red"));
            depositerrormessage.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    depositerrormessage.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        else {
            depositamountline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
            depositamountlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        }

        String amount = databaseWork.GetInformation(depositaccountidtextfield.getText(),"CurrentBalance");
        if(amount == null) amount = "0";
        double value = Double.valueOf(amount) + Double.valueOf(depositamounttextfield.getText());
        databaseWork.Update("CurrentBalance",String.valueOf(value),depositaccountidtextfield.getText());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        databaseWork.transactionInsert(depositaccountidtextfield.getText(), "Deposit", depositamounttextfield.getText(),"0",agentid.getText(), "Agent",dtf.format(now));

        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("insert into demo.a%s (AccountId, TransactionType, Amount, Date) Values ( '%s', 'Deposit', '%s', '%s')",agentid.getText(),depositaccountidtextfield.getText(),depositamounttextfield.getText(),dtf.format(now));
        st.executeUpdate(query);

        st = new DatabaseFacilites().getConnection().createStatement();
        query = String.format("select * from demo.agentinformation where AccountId = '%s'",agentid.getText());
        ResultSet res = st.executeQuery(query);
        res.next();
        double dat;
        if(res.getString("Balance") == null || res.getString("Balance").equals("")) dat = 0;
        else  dat = Double.valueOf(res.getString("Balance"));
        double u = Double.valueOf(depositamounttextfield.getText());
        u = u - (u*0.005);
        dat -= u;
        if(dat < -5000) {
            limitcrossed.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    limitcrossed.setVisible(false);
                    depositaccountidtextfield.setText("");
                    depositamounttextfield.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        query = String.format("update demo.agentinformation set Balance = '%s' where AccountId = '%s'",String.valueOf(dat),agentid.getText());
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        depositsuccessmessage.setVisible(true);
        EnteringDepositPaneRefresh();
        Timer timer = new Timer();
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                depositsuccessmessage.setVisible(false);
                timer.cancel();
            }
        };
        timer.schedule(timertask,3000);
        return;
    }

    public void EnteringAgentMode() {
        AgentModePaneRefresh();
        VisibilityUpdate(agentmodepane);
    }
    @FXML
    private Label invalidid,invalidid1;
    public void EnteringAgentModeBankingPane() throws SQLException {
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query  = String.format("select * from demo.agentinformation where AccountId = '%s'",agentid.getText());
        ResultSet res = st.executeQuery(query);
        if(!res.next()) {
            invalidid.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    invalidid.setVisible(false);
                    agentid.setText("");
                    agentpin.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        if(!agentpin.getText().equals(res.getString("Password"))) {
            invalidid1.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    invalidid1.setVisible(false);
                    agentid.setText("");
                    agentpin.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        st = new DatabaseFacilites().getConnection().createStatement();
        query = String.format("select * from demo.agentinformation where AccountId = '%s'",agentid.getText());
        res = st.executeQuery(query);
        res.next();
        String x;
        if(res.getString("Balance") == null) x = "0";
        else x = res.getString("Balance");
        agentbalance.setText("Balance : " + x + " BDT");

        VisibilityUpdate(agentmodebankingpane);
    }
    @FXML
    private Label agentbalance;
    public void AgentModePaneRefresh() {
        agentpin.setText("");
        agentid.setText("");
        agentidline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
        agentpinline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
        agentidlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        agentpinlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
    }
    public void EnteringWithdrawBankingPane() {
        EnteringWithdrawPaneRefresh();
        VisibilityUpdate(bankingwithdrawpane);
    }
    public void EnteringWithdrawPaneRefresh() {
        withdrawamounttextfield.setText("");
        withdrawamountlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        withdrawamountline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
        withdrawaccountidtextfield.setText("");
        withdrawaccountidlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        withdrawaccountidline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
    }
    public void BackFromEnteringWithdrawPane() {
        VisibilityUpdate(bankingpane);
    }
    public void Withdraw() throws SQLException {
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        if(!databaseWork.UserExist("AccountId",withdrawaccountidtextfield.getText(),"demo.agentinformation")) {
            withdrawaccountidline.setStroke(Paint.valueOf("red"));
            withdrawaccountidlabel.setTextFill(Paint.valueOf("red"));
            withdrawerrormessage.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    withdrawerrormessage.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        else {
            withdrawaccountidline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
            withdrawaccountidlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        }

        if(!Validation.checkDepositAmount(withdrawamounttextfield.getText())) {
            withdrawamountline.setStroke(Paint.valueOf("red"));
            withdrawamountlabel.setTextFill(Paint.valueOf("red"));
            withdrawerrormessage1.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    withdrawerrormessage1.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        else {
            withdrawamountline.setStroke(Paint.valueOf(ThemeFacilities.backcolor));
            withdrawamountlabel.setTextFill(Paint.valueOf(ThemeFacilities.backcolor));
        }

        String amount = databaseWork.GetInformation(UserAccountId.getText(),"CurrentBalance");
        if(amount == null) amount = "0";
        Double charge = Double.valueOf(withdrawamounttextfield.getText())*0.015;
        double value = Double.valueOf(amount) - Double.valueOf(withdrawamounttextfield.getText()) - charge;
        if(value<0.0) {
            withdrawerrormessage2.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    withdrawerrormessage2.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        charge = Double.valueOf(df.format(charge));
        value = Double.valueOf(df.format(value));
        databaseWork.Update("CurrentBalance",String.valueOf(value),UserAccountId.getText());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        databaseWork.transactionInsert(UserAccountId.getText(), "Withdraw", withdrawamounttextfield.getText(),String.valueOf(charge),withdrawaccountidtextfield.getText(), "Agent",dtf.format(now));
        currentbalancelabel.setText(databaseWork.GetInformation(UserAccountId.getText(),"CurrentBalance") + " BDT");
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("insert into demo.a%s (AccountId, TransactionType, Amount, Date) Values ( '%s', 'Withdraw', '%s', '%s')",withdrawaccountidtextfield.getText(),UserAccountId.getText(),withdrawamounttextfield.getText(),dtf.format(now));
        st.executeUpdate(query);
        st = new DatabaseFacilites().getConnection().createStatement();
        query = String.format("select * from demo.agentinformation where AccountId = '%s'",withdrawaccountidtextfield.getText());
        ResultSet res = st.executeQuery(query);
        res.next();
        double dat;
        if(res.getString("Balance") == null || res.getString("Balance").equals("")) dat = 0;
        else  dat = Double.valueOf(res.getString("Balance"));
        dat += Double.valueOf(withdrawamounttextfield.getText());
        query = String.format("update demo.agentinformation set Balance = '%s' where AccountId = '%s'",String.valueOf(dat),withdrawaccountidtextfield.getText());
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        EnteringWithdrawPaneRefresh();
        withdrawsuccessmessage.setVisible(true);
        Timer timer = new Timer();
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                withdrawsuccessmessage.setVisible(false);
                timer.cancel();
            }
        };
        timer.schedule(timertask,3000);
        return;
    }
    @FXML
    private LineChart<?,?>linechart;
    @FXML
    private Pane filteringchartpaneerrormessage;
    @FXML
    private Pane chartshowingpane, filteringchartpane;
    @FXML
    private BarChart barchart;
    @FXML
    private AreaChart areachart;
    @FXML
    private StackedBarChart stackedbarchart;



    public void chart() throws SQLException {
        String startdate = fromyearchoice.getValue() + "/" + frommonthchoice.getValue() + "/" + fromdaychoice.getValue();
        String todate = toyearchoice.getValue() + "/" + tomonthchoice.getValue() + "/" + todaychoice.getValue();
        if(startdate.compareTo(todate) > 0) {
            filteringchartpaneerrormessage.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    filteringchartpaneerrormessage.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        Map<String,Double> data = databaseWork.GetInformationTransaction(startdate,todate,subjectonchoice.getValue(),"Male");
        Map<String,Double> data2 = databaseWork.GetInformationTransaction(startdate,todate,subjectonchoice.getValue(),"Female");
        Map<String,Double> data3 = databaseWork.GetInformationTransaction(startdate,todate,subjectonchoice.getValue(),"Custom");


        Double val = 0.0;
        if(analysistypechoice.getValue().equals("Cumulative")) {
            for(String i: data.keySet()) {
                val += data.get(i);
                data.put(i,val);
            }
            for(String i: data2.keySet()) {
                val += data2.get(i);
                data2.put(i,val);
            }
            for(String i: data3.keySet()) {
                val += data3.get(i);
                data3.put(i,val);
            }
        }
        VisibilityUpdate(adminchoicepane);
        chartshowingpane.setVisible(true);


        if(charttypechoice.getValue().equals("Line Chart")) {
            chartRefresh();
            linechart.setVisible(true);
            linechart.getData().addAll(chartFacilities.createSereis(data,"Male"),chartFacilities.createSereis(data2,"Female"),chartFacilities.createSereis(data3,"Custom"));
        }
        else if(charttypechoice.getValue().equals("Bar Diagram")) {
            chartRefresh();
            barchart.setVisible(true);
            barchart.getData().addAll(chartFacilities.createSereis(data,"Male"),chartFacilities.createSereis(data2,"Female"),chartFacilities.createSereis(data3,"Custom"));
        }
        else if(charttypechoice.getValue().equals("Area Chart")) {
            chartRefresh();
            areachart.setVisible(true);
            areachart.getData().addAll(chartFacilities.createSereis(data,"Male"),chartFacilities.createSereis(data2,"Female"),chartFacilities.createSereis(data3,"Custom"));
        }
        else if(charttypechoice.getValue().equals("Stacked Bar Diagram")) {
            chartRefresh();
            stackedbarchart.setVisible(true);
            stackedbarchart.getData().addAll(chartFacilities.createSereis(data,"Male"),chartFacilities.createSereis(data2,"Female"),chartFacilities.createSereis(data3,"Custom"));
        }

    }
    public void chartRefresh() {
        linechart.getData().clear();
        barchart.getData().clear();
        areachart.getData().clear();
        stackedbarchart.getData().clear();
        linechart.setVisible(false);
        barchart.setVisible(false);
        areachart.setVisible(false);
        stackedbarchart.setVisible(false);
    }
    public void BackFromChartShowingPane() {
        VisibilityUpdate(adminchoicepane);
        filteringchartpane.setVisible(true);
    }

    @FXML
    private ComboBox<String> fromyearchoice, frommonthchoice, fromdaychoice, toyearchoice, tomonthchoice, todaychoice, charttypechoice, subjectonchoice, analysistypechoice;


    public void VisualizationFilter() {
        VisualizationFileterRefresh();
        VisibilityUpdate(adminchoicepane);
        filteringchartpane.setVisible(true);

        for(int i = 2022; i<=2090; i++) fromyearchoice.getItems().add(String.valueOf(i));
        fromyearchoice.setValue("2022");
        for(int i = 1; i<=12; i++) {
            if(i<=9) frommonthchoice.getItems().add("0" + String.valueOf(i));
            else frommonthchoice.getItems().add(String.valueOf(i));
        }

        frommonthchoice.setValue("01");
        for(int i = 1; i<=31; i++) {
            if(i<=9) fromdaychoice.getItems().add("0" + String.valueOf(i));
            else fromdaychoice.getItems().add(String.valueOf(i));
        }
        fromdaychoice.setValue("01");
        for(int i = Integer.valueOf(fromyearchoice.getValue()); i<=2090; i++) toyearchoice.getItems().add(String.valueOf(i));
        toyearchoice.setValue("2022");
        for(int i = 1; i<=12; i++) {
            if(i<=9) tomonthchoice.getItems().add("0" + String.valueOf(i));
            else tomonthchoice.getItems().add(String.valueOf(i));
        }
        tomonthchoice.setValue("01");
        for(int i = 1; i<=31; i++) {
            if(i<=9) todaychoice.getItems().add("0" + String.valueOf(i));
            else todaychoice.getItems().add(String.valueOf(i));
        }
        todaychoice.setValue("01");
        charttypechoice.getItems().addAll("Line Chart", "Bar Diagram", "Area Chart",  "Stacked Bar Diagram");
        charttypechoice.setValue("Line Chart");
        subjectonchoice.getItems().addAll("Deposit", "Withdraw", "Send Money", "Payment", "Recharge", "Reserved Money");
        subjectonchoice.setValue("Deposit");
        analysistypechoice.getItems().addAll("Cumulative","Raw Data");
        analysistypechoice.setValue("Raw Data");


    }
    public void VisualizationFileterRefresh() {
        fromyearchoice.getItems().clear();
        fromdaychoice.getItems().clear();
        frommonthchoice.getItems().clear();
        todaychoice.getItems().clear();
        tomonthchoice.getItems().clear();
        toyearchoice.getItems().clear();
        charttypechoice.getItems().clear();
        subjectonchoice.getItems().clear();
        analysistypechoice.getItems().clear();
    }
    @FXML
    private Pane administrationpane, adminchoicepane;
    @FXML
    private Label adminidlabel;

    @FXML
    private Line adminidline;

    @FXML
    private TextField adminidtextfield;
    @FXML
    private Label adminpinlabel;

    @FXML
    private Line adminpinline;
    @FXML
    private Pane adminerrormessage;

    @FXML
    private TextField adminpintextfield;
    public void EnteringAdminPane() {
        EnteringAdminPaneRefresh();
        VisibilityUpdate(administrationpane);
    }
    public void EnteringAdminPaneRefresh() {
        adminidtextfield.setText("");
        adminpintextfield.setText("");
        adminidlabel.setTextFill(Paint.valueOf("1F2D3A"));
        adminpinlabel.setTextFill(Paint.valueOf("1F2D3A"));
        adminidline.setStroke(Paint.valueOf("1F2D3A"));
        adminpinline.setStroke(Paint.valueOf("1F2D3A"));
    }
    public void EnteringAdminChoicePane() {
        if(!(adminidtextfield.getText().equals("010101") && adminpintextfield.getText().equals("123456"))) {
            adminidline.setStroke(Paint.valueOf("red"));
            adminpinline.setStroke(Paint.valueOf("red"));
            adminidlabel.setTextFill(Paint.valueOf("red"));
            adminpinlabel.setTextFill(Paint.valueOf("red"));
            adminerrormessage.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    adminerrormessage.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        VisibilityUpdate(adminchoicepane);
    }
    @FXML
    private JFXTreeTableView<Model2> agenttable;

    private TreeTableColumn<Model2, String> agentnamecol = new TreeTableColumn<>();
    private TreeTableColumn<Model2, String> agentindexcol = new TreeTableColumn<>();
    private TreeTableColumn<Model2, String>  agentbalancecol = new TreeTableColumn<>();
    private TreeTableColumn<Model2, String> agentemailcol = new TreeTableColumn<>();
    private TreeTableColumn<Model2, String> agentphonenumbercol = new TreeTableColumn<>();
    private TreeTableColumn<Model2, String> agentidcol = new TreeTableColumn<>();
    ObservableList<Model2> list2;
    


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        useraccountidcol.setCellValueFactory(new PropertyValueFactory<usertransModel,String>("accountid"));
        useramountcol.setCellValueFactory(new PropertyValueFactory<usertransModel,String>("amount"));
        userindexcol.setCellValueFactory(new PropertyValueFactory<usertransModel,String>("index"));
        userchargecol.setCellValueFactory(new PropertyValueFactory<usertransModel,String>("charge"));
        userdatecol.setCellValueFactory(new PropertyValueFactory<usertransModel,String>("date"));
        usernamecol.setCellValueFactory(new PropertyValueFactory<usertransModel,String>("username"));
        usertranscol.setCellValueFactory(new PropertyValueFactory<usertransModel,String>("transtype"));


        agenttransaccountidcol.setCellValueFactory(new PropertyValueFactory<agenttranshist,String>("accountid"));
        agenttranstypecol.setCellValueFactory(new PropertyValueFactory<agenttranshist,String>("type"));
        agenttransamountcol.setCellValueFactory(new PropertyValueFactory<agenttranshist,String>("amount"));
        agenttransdatecol.setCellValueFactory(new PropertyValueFactory<agenttranshist,String>("date"));
        agenttransindexcol.setCellValueFactory(new PropertyValueFactory<agenttranshist,String>("index"));
        agentnamecol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().Name;
            }
        });
        agentindexcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().agentid;
            }
        });
        agentbalancecol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().balance;
            }
        });
        agentemailcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().email;
            }
        });
        agentphonenumbercol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().phonenumber;
            }
        });
        agentidcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().agentid;
            }
        });


        namecol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().name;
            }
        });
        agecol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().age;
            }
        });
        occupationcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().occupation;
            }
        });
        gendercol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().gender;
            }
        });
        indexcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().index;
            }
        });
        emailcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().email;
            }
        });
        phonenumbercol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().phonenumber;
            }
        });
        accountidcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> modelStringCellDataFeatures) {
                return modelStringCellDataFeatures.getValue().getValue().accountid;
            }
        });
        agentnamecol.setText("Name");
        agentnamecol.setPrefWidth(200);
        agentnamecol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        agentidcol.setText("Agent Id");
        agentidcol.setPrefWidth(200);
        agentidcol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        agentindexcol.setText("Index");
        agentindexcol.setPrefWidth(200);
        agentindexcol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        agentphonenumbercol.setText("Phone Number");
        agentphonenumbercol.setPrefWidth(200);
        agentphonenumbercol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        agentemailcol.setText("Email");
        agentemailcol.setPrefWidth(200);
        agentemailcol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        agentbalancecol.setText("Balance");
        agentbalancecol.setPrefWidth(200);
        agentbalancecol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        agenttable.getColumns().addAll(agentindexcol,agentidcol,agentnamecol,agentbalancecol,agentemailcol,agentphonenumbercol);
        list2 = FXCollections.observableArrayList();
        TreeItem<Model2> root2 = new RecursiveTreeItem<Model2>(list2, RecursiveTreeObject::getChildren);
        agenttable.setRoot(root2);
        agenttable.setShowRoot(false);
        agenttable.setRowFactory(tv -> {
            TreeTableRow<Model2> row = new TreeTableRow<Model2>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Model2 clickedRow = row.getItem();
                    try {
                        individualagentinformation(clickedRow.getAgentid());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row ;
        });
        agecol.setText("Age");
        agecol.setPrefWidth(200);
        agecol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center;");
        namecol.setText("Name");
        namecol.setPrefWidth(200);
        namecol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        emailcol.setText("Email");
        emailcol.setPrefWidth(200);
        emailcol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        accountidcol.setText("Account Id");
        accountidcol.setPrefWidth(200);
        accountidcol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        phonenumbercol.setText("Phone Number");
        phonenumbercol.setPrefWidth(200);
        phonenumbercol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        occupationcol.setText("Occupation");
        occupationcol.setPrefWidth(200);
        occupationcol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        gendercol.setText("Gender");
        gendercol.setPrefWidth(200);
        gendercol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        indexcol.setText("Index");
        indexcol.setPrefWidth(200);
        indexcol.setStyle("-fx-text-fill: #1F2D3A;-fx-background-color:#9E9E9E;-fx-alignment:center");
        databasetable.getColumns().addAll(indexcol,accountidcol,namecol,agecol,gendercol,phonenumbercol,emailcol,occupationcol);
        list = FXCollections.observableArrayList();
        TreeItem<Model> root = new RecursiveTreeItem<Model>(list, RecursiveTreeObject::getChildren);
        databasetable.setRoot(root);
        databasetable.setShowRoot(false);
        databasetable.setRowFactory(tv -> {
            TreeTableRow<Model> row = new TreeTableRow<Model>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Model clickedRow = row.getItem();
                    try {
                        makeProfile2(clickedRow.getAccountId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row ;
        });
        databasegender.getItems().addAll("Everyone", "Male", "Female","Custom");
        databasegender.setValue("Everyone");
        databasemaritalstatus.getItems().addAll("Everyone", "Married", "Unmarried");
        databasemaritalstatus.setValue("Everyone");
        databasereligion.getItems().addAll("Everyone","Islam","Hindu","Christian","Budha","Other");
        databasereligion.setValue("Everyone");
        databaseincome.getItems().addAll("For All", "up to 5000 BDT", "up to 10000 BDT", "up to 25000 BDT", "up to 50000 BDT", "up to 100000 BDT", "More than 100000 BDT");
        databaseincome.setValue("For All");
        for(int i = 1900; i<=2090; i++) databaseyear.getItems().add(String.valueOf(i));
        databaseyear.getItems().add("None");
        for(int i = 1; i<=12; i++) databasemonth.getItems().add(String.valueOf(i));
        databasemonth.getItems().add("None");
        for(int i = 1; i<=31; i++) databaseday.getItems().add(String.valueOf(i));
        databaseday.getItems().add("None");
        databaseyear.setValue("None");
        databasemonth.setValue("None");
        databaseday.setValue("None");

    }

    @FXML
    private Pane databasefilteringpane, databaseshowingpane;
    @FXML
    private Pane profileouterpane, profilepersonalinformationpane, profileaddressinformationpane, profiletransactionactivitypane;
    @FXML
    private Label profileouterpanelabel, profilefathername, profilemothername,profilegender, profilereligion, profileoccupation, profileage, profiledateofbirth, profilenid,profilenationality , profileincome;
    @FXML
    private Label profilecity, profiledistrict, profilepostoffice, profilepostalcode , profiletotaltransactions,profilename,profilebalance;
    @FXML
    private PieChart profilepiechart;
    @FXML
    private JFXButton backtodatabase;
    int profileanimationcounter = 0;
    public void makeProfile(String accountid, int flag) throws SQLException {
        if(flag == 1) {
            backtodatabase.setVisible(true);
        }
        else {
            backtodatabase.setVisible(false);
        }
        VisibilityUpdate(profilepane);
        profilepersonalinformationpane.setVisible(true);
        profilepane.setVisible(true);
        String query = "select * from Demo.UserInformation where AccountId = '" + accountid + "'";
        Statement statement = new DatabaseFacilites().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        profilename.setText(resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
        String temp = "0";
        if(resultSet.getString("CurrentBalance") != null) temp = resultSet.getString("CurrentBalance");
        profilebalance.setText("Balance : " + temp + " BDT");
        profilefathername.setText("Father's Name : " + resultSet.getString("FatherName"));
        profilemothername.setText("Mother's Name : " + resultSet.getString("MotherName"));
        profilenid.setText("NID : " + resultSet.getString("NID"));
        profilegender.setText("Gender : " + resultSet.getString("Gender"));
        profilenationality.setText("Nationality : " + resultSet.getString("Nationality"));
        profiledateofbirth.setText("Date of Birth : " + resultSet.getString("DateOfBirth"));
        profilereligion.setText("Religion : " + resultSet.getString("Religion"));
        profileoccupation.setText("Occupation : " + resultSet.getString("Occupation"));
        profileincome.setText("Income : " + resultSet.getString("Income"));
        profileage.setText("Age : " + String.valueOf(ageCalculator(resultSet.getString("DateOfBirth"))));
        profilecity.setText("City : " + resultSet.getString("City"));
        profiledistrict.setText("District : " + resultSet.getString("District"));
        profilepostoffice.setText("Post Office : " + resultSet.getString("PostOffice"));
        profilepostalcode.setText("Postal Code : " + resultSet.getString("PostalCode"));
        String query2 = "select * from Demo.R" + accountid;
        int row = 0,sendmoney = 0, deposit = 0, withdraw = 0, payment = 0, recharge = 0;
        ResultSet resultSet2 = statement.executeQuery(query2);
        while(resultSet2.next()) {
            row++;
            if(resultSet2.getString("TransactionType").equals("Deposit")) deposit++;
            if(resultSet2.getString("TransactionType").equals("Withdraw")) withdraw++;
            if(resultSet2.getString("TransactionType").equals("SendMoney")) sendmoney++;
            if(resultSet2.getString("TransactionType").equals("Payment")) payment++;
            if(resultSet2.getString("TransactionType").equals("Recharge")) recharge++;
        }
        profiletotaltransactions.setText("Total Transacations : " + String.valueOf(row));
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Deposit", deposit),
                        new PieChart.Data("Withdraw", withdraw),
                        new PieChart.Data("Payment", payment),
                        new PieChart.Data("Send Money", sendmoney),
                        new PieChart.Data("Recharge", recharge));
        profilepiechart.getData().clear();
        profilepiechart.getData().addAll(pieChartData);
        profilepiechart.setTitle("Transactions Overview");

    }
    @FXML
    private Pane profilepersonalinformationpane1,profilepane1;
    @FXML
    private Label profilename1,profilebalance1,profilefathername1,profilemothername1,profilenid1;
    @FXML
    private Label profilegender1,profilenationality1,profiledateofbirth1,profilereligion1,profileoccupation1;
    @FXML
    private Label profileincome1,profileage1, profilecity1,profiledistrict1,profilepostoffice1,profilepostalcode1;
    @FXML
    private Label profiletotaltransactions1;
    @FXML
    private PieChart profilepiechart1;
    public void makeProfile2(String accountid) throws SQLException {

        VisibilityUpdate(adminchoicepane);
        profilepane1.setVisible(true);
        profilepersonalinformationpane1.setVisible(true);
        profilepane1.setVisible(true);
        String query = "select * from Demo.UserInformation where AccountId = '" + accountid + "'";
        Statement statement = new DatabaseFacilites().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        profilename1.setText(resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
        String temp = "0";
        if(resultSet.getString("CurrentBalance") != null) temp = resultSet.getString("CurrentBalance");
        profilebalance1.setText("Balance : " + temp + " BDT");
        profilefathername1.setText("Father's Name : " + resultSet.getString("FatherName"));
        profilemothername1.setText("Mother's Name : " + resultSet.getString("MotherName"));
        profilenid1.setText("NID : " + resultSet.getString("NID"));
        profilegender1.setText("Gender : " + resultSet.getString("Gender"));
        profilenationality1.setText("Nationality : " + resultSet.getString("Nationality"));
        profiledateofbirth1.setText("Date of Birth : " + resultSet.getString("DateOfBirth"));
        profilereligion1.setText("Religion : " + resultSet.getString("Religion"));
        profileoccupation1.setText("Occupation : " + resultSet.getString("Occupation"));
        profileincome1.setText("Income : " + resultSet.getString("Income"));
        profileage1.setText("Age : " + String.valueOf(ageCalculator(resultSet.getString("DateOfBirth"))));
        profilecity1.setText("City : " + resultSet.getString("City"));
        profiledistrict1.setText("District : " + resultSet.getString("District"));
        profilepostoffice1.setText("Post Office : " + resultSet.getString("PostOffice"));
        profilepostalcode1.setText("Postal Code : " + resultSet.getString("PostalCode"));
        String query2 = "select * from Demo.R" + accountid;
        int row = 0,sendmoney = 0, deposit = 0, withdraw = 0, payment = 0, recharge = 0;
        ResultSet resultSet2 = statement.executeQuery(query2);
        while(resultSet2.next()) {
            row++;
            if(resultSet2.getString("TransactionType").equals("Deposit")) deposit++;
            if(resultSet2.getString("TransactionType").equals("Withdraw")) withdraw++;
            if(resultSet2.getString("TransactionType").equals("SendMoney")) sendmoney++;
            if(resultSet2.getString("TransactionType").equals("Payment")) payment++;
            if(resultSet2.getString("TransactionType").equals("Recharge")) recharge++;
        }
        profiletotaltransactions1.setText("Total Transacations : " + String.valueOf(row));
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Deposit", deposit),
                        new PieChart.Data("Withdraw", withdraw),
                        new PieChart.Data("Payment", payment),
                        new PieChart.Data("Send Money", sendmoney),
                        new PieChart.Data("Recharge", recharge));
        profilepiechart1.getData().clear();
        profilepiechart1.getData().addAll(pieChartData);
        profilepiechart1.setTitle("Transactions Overview");

    }
    public void backtodatabase() {
        VisibilityUpdate(adminchoicepane);
        databaseshowingpane.setVisible(true);
    }
    int profileanimationflag = 0;
    public void profileanimationback() {
        profileanimationcounter--;
        controlProfileAnimation();
    }
    public void profileanimationnext() {
        profileanimationcounter++;
        controlProfileAnimation();
    }
    public void controlProfileAnimation() {
//        System.out.println(profileanimationcounter);
        profileanimationcounter%=3;
        Pane temppane;
        if(profileanimationcounter == 0) {
            profileouterpanelabel.setText("Personal Information");
            VisibilityUpdate(profilepersonalinformationpane);
            temppane = profilepersonalinformationpane;
        }
        else if(profileanimationcounter == 1 || profileanimationcounter == -2) {
            profileouterpanelabel.setText("Transaction Activity");
            VisibilityUpdate(profiletransactionactivitypane);
            temppane = profiletransactionactivitypane;
        }
        else {
            profileouterpanelabel.setText("Address");
            VisibilityUpdate(profileaddressinformationpane);
            temppane = profileaddressinformationpane;
        }
        profilepane.setVisible(true);

    }
    int profileanimationcounter2 = 0;
    public void profileanimationback2() {
        profileanimationcounter2--;
        controlProfileAnimation2();
    }
    public void profileanimationnext2() {
        profileanimationcounter2++;
        controlProfileAnimation2();
    }
    @FXML
    private Label profileouterpanelabel1;
    @FXML
    private Pane profiletransactionactivitypane1,profileaddressinformationpane1;
    public void controlProfileAnimation2() {
//        System.out.println(profileanimationcounter);
        profileanimationcounter2%=3;
        Pane temppane;
        if(profileanimationcounter2 == 0) {
            profileouterpanelabel1.setText("Personal Information");
            VisibilityUpdate(adminchoicepane);
            profilepane1.setVisible(true);
            profilepersonalinformationpane1.setVisible(true);
            temppane = profilepersonalinformationpane1;
        }
        else if(profileanimationcounter2 == 1 || profileanimationcounter2 == -2) {
            profileouterpanelabel1.setText("Transaction Activity");
            VisibilityUpdate(adminchoicepane);
            profilepane1.setVisible(true);
            profiletransactionactivitypane1.setVisible(true);
            temppane = profiletransactionactivitypane1;
        }
        else {
            profileouterpanelabel1.setText("Address");
            VisibilityUpdate(adminchoicepane);
            profilepane1.setVisible(true);
            profileaddressinformationpane1.setVisible(true);
            temppane = profileaddressinformationpane1;
        }
        profilepane1.setVisible(true);

    }


    public void VisibilityUpdate(Pane pane) {
        Themepane.setVisible(false);
        changepasswordpane1.setVisible(false);
        usertranshist.setVisible(false);
        rechargepane.setVisible(false);
        sendmoneypane1.setVisible(false);
        sendmoneypane.setVisible(false);
        updateagentbalancepane.setVisible(false);
        chartshowingpane.setVisible(false);
        agentprofilepane.setVisible(false);
        agentinformationpane.setVisible(false);
        profileaddressinformationpane1.setVisible(false);
        profilepersonalinformationpane1.setVisible(false);
        profiletransactionactivitypane1.setVisible(false);
        profilepane1.setVisible(false);
        agentaccountsuccessmsg.setVisible(false);
        agentsignup.setVisible(false);
        toppane.setVisible(false);
        userpane.setVisible(false);
        profilepersonalinformationpane.setVisible(false);
        profileaddressinformationpane.setVisible(false);
        profiletransactionactivitypane.setVisible(false);
        filteringchartpane.setVisible(false);
        //chartshowingpane.setVisible(false);
        databasefilteringpane.setVisible(false);
        databaseshowingpane.setVisible(false);
        bankingwithdrawpane.setVisible(false);
        agentmodebankingpane.setVisible(false);
        agentmodepane.setVisible(false);
        bankingdepositpane.setVisible(false);
        profilepane.setVisible(false);
        homepane.setVisible(false);
        bankingpane.setVisible(false);
        administrationpane.setVisible(false);
        adminchoicepane.setVisible(false);
        pane.setVisible(true);
    }
    @FXML
    private JFXTreeTableView<Model> databasetable;

    private TreeTableColumn<Model, String> agecol = new TreeTableColumn<>();
    private TreeTableColumn<Model, String> namecol = new TreeTableColumn<>();
    private TreeTableColumn<Model, String>  gendercol = new TreeTableColumn<>();
    private TreeTableColumn<Model, String> emailcol = new TreeTableColumn<>();
    private TreeTableColumn<Model, String> phonenumbercol = new TreeTableColumn<>();
    private TreeTableColumn<Model, String> accountidcol = new TreeTableColumn<Model,String>();
    private TreeTableColumn<Model, String> occupationcol = new TreeTableColumn<>();
    private TreeTableColumn<Model, String> indexcol = new TreeTableColumn<>();
    ObservableList<Model> list;
    @FXML
    private ComboBox<String> databaseyear,databasemonth,databaseday,databasegender,databaseincome,databasemaritalstatus,databasereligion;
    @FXML
    private TextField databasename,databaseaccountid,databasefathername,databasemothername,databasecity,databasedistrict,databasepostoffice,databasepostalcode,databaseemail,databasenid,databasephonenumber,databaseoccupation;

    public void EnteringDatabaseShowingPane() throws SQLException {
        list.clear();
        VisibilityUpdate(adminchoicepane);
        databaseshowingpane.setVisible(true);
        String query = "select  * from Demo.UserInformation where ";
        query += ("AccountId like '" + databaseaccountid.getText()+"%' AND ");
        query += ("Email like '" + databaseemail.getText()+"%' AND ");
        query += ("NID like '" + databasenid.getText()+"%' AND ");
        query += ("PhoneNumber like '" + databasephonenumber.getText()+"%' AND ");
        query += ("Occupation like '" + databaseoccupation.getText()+"%' AND ");
        query += ("FatherName like '" + databasefathername.getText()+"%' AND ");
        query += ("MotherName like '" + databasemothername.getText()+"%' AND ");
        query += ("City like '" + databasecity.getText()+"%' AND ");
        query += ("District like '" + databasedistrict.getText() + "%' AND ");
        query += ("PostOffice like '" + databasepostoffice.getText() + "%' AND ");
        query += ("PostalCode like '" + databasepostalcode.getText() + "%' AND ");
        query += ("DateOfBirth like '");
        String date = "";
        if(databaseyear.getValue().equals("None")) date = "%";
        else {
            date += databaseyear.getValue();
            date += "-";
            if(databasemonth.getValue().equals("None")) date += "%";
            else {
                if(databasemonth.getValue().length() == 1) date += "0";
                date += databasemonth.getValue();
            }
            date += "-";
            if(databaseday.getValue().equals("None")) date += "%";
            else {
                if(databaseday.getValue().length() == 1) date += "0";
                date += databaseday.getValue();
            }
        }
        query += (date + "' AND ");
        query += ("Gender like '");
        if(databasegender.getValue().equals("Everyone")) query += "";
        else query += databasegender.getValue();
        query += "%' AND ";
        query += ("MaritalStatus like '");
        if(databasemaritalstatus.getValue().equals("Everyone")) query += "";
        else query += databasemaritalstatus.getValue();
        query += "%' AND ";
        query += ("Religion like '");
        if(databasereligion.getValue().equals("Everyone")) query += "";
        else query += databasereligion.getValue();
        query += "%' AND ";
        query += ("Income like '");
        if(databaseincome.getValue().equals("For All")) query += "";
        else query += databaseincome.getValue();
        query += "%'";

        Statement statement = new DatabaseFacilites().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int cnt = 1;
        while(resultSet.next()) {
            String name = (resultSet.getString("FirstName") + " " + resultSet.getString("LastName")).toLowerCase();
            String name2 = databasename.getText().toLowerCase();
            int flag = 0;
            for(int i = 0,j = 0; i<name.length() && j<name2.length(); i++,j++) {
                if(name.charAt(i) != name2.charAt(j)) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) continue;
            int tempage = ageCalculator(resultSet.getString("DateOfBirth"));
            list.addAll(new Model(resultSet.getString("FirstName")+" " + resultSet.getString("LastName"),String.valueOf(tempage),resultSet.getString("Gender"),resultSet.getString("Occupation"),resultSet.getString("PhoneNumber"),resultSet.getString("Email"),resultSet.getString("AccountId"),String.valueOf(cnt)));
            cnt++;
        }

    }

    public void EnteringDatabaseFilteringPane() {
        VisibilityUpdate(adminchoicepane);
        databasefilteringpane.setVisible(true);

    }
    public int ageCalculator(String date) {
        String[] x = new String[3];
        x = date.split("-");
        int year = Integer.valueOf(x[0]);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        int ans = Integer.valueOf(dtf.format(now)) - Integer.valueOf(x[0]);
        dtf = DateTimeFormatter.ofPattern("MM");
        now = LocalDateTime.now();
        if(Integer.valueOf(dtf.format(now))>Integer.valueOf(x[1])) return ans;
        if(Integer.valueOf(dtf.format(now))<Integer.valueOf(x[1])) return ans-1;
        dtf = DateTimeFormatter.ofPattern("dd");
        now = LocalDateTime.now();
        if(Integer.valueOf(dtf.format(now))>=Integer.valueOf(x[2])) return ans;
        if(Integer.valueOf(dtf.format(now))<Integer.valueOf(x[2])) return ans-1;
        return  ans;
    }
    public void BackFromDatabaseShowingPane() {

        VisibilityUpdate(adminchoicepane);
        databasefilteringpane.setVisible(true);
    }

    @FXML
    private  Pane UserMenuPane;
    @FXML
    private Line line1,line2,line3,line4,line5,line6,line7,line8,line9;
    @FXML
    private JFXButton menubtn1,menubtn2,menubtn3,menubtn4,menubtn5,menubtn6,menubtn7,menubtn8;
    @FXML
    private Pane homemainpane,userpane;
    @FXML
    private Label copyrightmsg, contactemailmsg;

    public void refreshTheme() {
        AnchorPaneUser.getStylesheets().add(getClass().getResource(temptheme).toExternalForm());

    }
    @FXML
    private Pane Themepane;
    public void EnteringThemePane() {
        VisibilityUpdate(Themepane);
    }
    public void userpanel() {
        VisibilityUpdate(userpane);
    }
    public void agentpanel() {
        AgentModePaneRefresh();
        VisibilityUpdate(agentmodepane);
    }
    public void adminpanel() {
        EnteringAdminPaneRefresh();
        VisibilityUpdate(administrationpane);
    }
    @FXML
    private Pane toppane,agentsignup;
    public void AgentSignUp() {
        VisibilityUpdate(adminchoicepane);
        AgentSignUpRefresh();
        agentsignup.setVisible(true);
        agentsignup1.setVisible(true);
        agentaccountsuccessmsg.setVisible(false);
    }
    @FXML
    private TextField agentfirstname, agentlastname, agentemail, agentnid, agentphonenumber, agentaddress;
    @FXML
    private Label agentfirsterror,agentlasterror,agentemailerror,agentphoneerror,agentniderror,agentaddresserror;
    public void AgentSignUpRefresh() {
        agentfirstname.setText("");
        agentlastname.setText("");
        agentemail.setText("");
        agentnid.setText("");
        agentphonenumber.setText("");
        agentaddress.setText("");
        agentlasterror.setVisible(false);
        agentemailerror.setVisible(false);
        agentphoneerror.setVisible(false);
        agentaddresserror.setVisible(false);
        agentniderror.setVisible(false);
        agentfirsterror.setVisible(false);
    }
    public void agentlastnameonkeytyped() {
        if(CheckAllCharacterWithoutSpace(agentlastname.getText()) == false) {
            agentlasterror.setVisible(true);
            return;
        }
        else agentlasterror.setVisible(false);
    }

    public void agentphonenumberonkeytype() {
        if(CheckValidPhoneNumber(agentphonenumber.getText()) == false) {
            agentphoneerror.setVisible(true);
            return;
        }
        else agentphoneerror.setVisible(false);
    }
    public void agentnidonkeytype() {
        if(Validation.CheckValidNid(agentnid.getText()) == false) {
            agentniderror.setVisible(true);
            return;
        }
        else agentniderror.setVisible(false);
    }
    public void agentfirstnameonkeytyped() {
        if(CheckAllCharacterWithoutSpace(agentfirstname.getText()) == false) {
            agentfirsterror.setVisible(true);
            return;
        }
        else agentfirsterror.setVisible(false);

    }
    public void agentsignupsubmit() throws Exception {
        int flag = 0;

        if(CheckAllCharacterWithoutSpace(agentfirstname.getText()) == false) {
            agentfirsterror.setVisible(true);
            flag++;

        }
        else agentfirsterror.setVisible(false);
        if(CheckAllCharacterWithoutSpace(agentlastname.getText()) == false) {
            agentlasterror.setVisible(true);
            flag++;

        }
        else agentlasterror.setVisible(false);

        if(MailFacilities.isAddressValid(agentemail.getText() + "@gmail.com") == false) {
            agentemailerror.setVisible(true);
            flag++;

        }
        else agentemailerror.setVisible(false);
        if(CheckValidPhoneNumber(agentphonenumber.getText()) == false) {
            agentphoneerror.setVisible(true);
            flag++;

        }
        else agentphoneerror.setVisible(false);

        if(Validation.CheckValidNid(agentnid.getText()) == false) {
            agentniderror.setVisible(true);
            flag++;

        }
        else agentniderror.setVisible(false);
        if(agentaddress.getText().equals("")) {
            agentaddresserror.setVisible(true);
            flag++;

        }
        else agentaddresserror.setVisible(false);
        if(flag == 0) {
            agentsignup1.setVisible(false);
            agentaccountsuccessmsg.setVisible(true);
            Map<String, String> map = new HashMap<>();
            map.put("FirstName",agentfirstname.getText());
            map.put("LastName",agentlastname.getText());
            map.put("Email",agentemail.getText());
            map.put("NID",agentnid.getText());
            map.put("Address",agentaddress.getText());
            map.put("PhoneNumber",agentphonenumber.getText());
            Random rand = new Random();
            int val = rand.nextInt(100000) + 300000;
            map.put("Password",String.valueOf(val));
            int cnt = new DatabaseFacilites().NumberOfUsers("Demo.AgentInformation");
            map.put("AccountId", String.valueOf(cnt + 200000));
            new DatabaseFacilites().Insert(map,"Demo.AgentInformation");
            String Body = String.format("Dear %s %s,\n You are now an agent of ProKash. Thanks for joining our team.\n Your " +
                    "ProKash Ageent Id %s\n Your Password is %s\n. Change your password from app to secure your account.",
                    firstname.getText(),lastname.getText(),String.valueOf(cnt+200000),String.valueOf(val));
            String subject = "Agent Information";
            MailFacilities.sendMail(agentemail.getText() + "@gmail.com",subject,Body);
            Statement st = new DatabaseFacilites().getConnection().createStatement();
            String query = String.format("create table demo.%s ( AccountId varChar(255), TransactionType varChar(255), Amount varChar(255), Date varChar(255) );", "a" + String.valueOf(cnt + 200000));
            st.executeUpdate(query);
        }
    }
    @FXML
    private Pane agentaccountsuccessmsg,agentsignup1;
    Pane arr[] = new Pane[25];
    public void backtotop() {
        VisibilityUpdate((toppane));
    }
    @FXML
    private Pane agentinformationpane;
    public void agentinformtaion() throws SQLException {
        VisibilityUpdate(adminchoicepane);
        agentinformationpane.setVisible(true);
        list2.clear();
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * FROM Demo.agentinformation");
        int i = 1;

        while(resultSet.next()) {
            String val = resultSet.getString("Balance");
            if(val == null) val = "0";
            list2.addAll(new Model2(String.valueOf(i), resultSet.getString("AccountId"),resultSet.getString("FirstName") + " " + resultSet.getString("LastName"),val,resultSet.getString("Email"),resultSet.getString("PhoneNumber") ));
            i++;
        }
    }
    @FXML
    private JFXButton backtoagentdatabase;
    @FXML
    private Pane agentprofilepane;
    @FXML
    private TableView<agenttranshist> agenttransactiontable;
    @FXML
    private TableColumn<agenttranshist,String> agenttransaccountidcol,agenttranstypecol,agenttransamountcol,agenttransdatecol,agenttransindexcol;
    @FXML
    private Label agentnamelabel, agentbalancelabel, agentemaillabel,agentphonenumberlabel,agentnidlabel,agentwithdrawlabel,agentdepositlabel;
    public void individualagentinformation(String id) throws SQLException {
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = "select * from demo.agentinformation";
        ResultSet resultSet = st.executeQuery(query);
        while(resultSet.next()) {
            if(resultSet.getString("AccountId").equals(id)) {
                agentnamelabel.setText(resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
                String val = resultSet.getString("Balance");
                if(val == null) val = "0";
                agentbalancelabel.setText("Balance : " + val + " BDT");
                agentemaillabel.setText(resultSet.getString("Email") + "@gmail.com");
                agentphonenumberlabel.setText(resultSet.getString("PhoneNumber"));
                agentnidlabel.setText(resultSet.getString("NID"));
                break;
            }
        }
        st = new DatabaseFacilites().getConnection().createStatement();
        query = "select * from demo.a" + id;
        resultSet = st.executeQuery(query);
        agenttransactiontable.getItems().clear();
        int i = 1,with = 0, depo = 0;
        while(resultSet.next()) {
            if(resultSet.getString("TransactionType").equals("Withdraw") && resultSet.getString("Amount") != null) with+=Integer.valueOf(resultSet.getString("Amount"));
            else if(resultSet.getString("TransactionType").equals("Deposit") && resultSet.getString("Amount") != null) depo += Integer.valueOf(resultSet.getString("Amount"));
            agenttransactiontable.getItems().add(new agenttranshist(resultSet.getString("AccountId"),resultSet.getString("TransactionType"),resultSet.getString("Amount"),resultSet.getString("Date"),String.valueOf(i)));
            i++;
        }
        agentwithdrawlabel.setText(String.valueOf(with) + " BDT");
        agentdepositlabel.setText(String.valueOf(depo) + " BDT");
        VisibilityUpdate(adminchoicepane);
        agentprofilepane.setVisible(true);

    }
    public void BackToAgentInformation() {
        VisibilityUpdate(adminchoicepane);
        agentinformationpane.setVisible(true);
    }
    @FXML
    private Label due, balance;
    @FXML
    private Pane updateagentbalancepane,payorreceiveagentbalancepane,payagentupdatebalancepane,receiveagentupdatebalancepane;
    public void updateAgentBalance() {
        VisibilityUpdate(adminchoicepane);
        updateagentbalancepane.setVisible(true);
        updateagentbalancepanerefresh();
    }
    @FXML
    private TextField agentupdateid;
    double val = 0;
    String tempemail;
    public void agentbalanceProceed() throws SQLException {
        if(agentupdateid.getText().equals("")) return;
        due.setVisible(true);
        balance.setVisible(true);
        payorreceiveagentbalancepane.setVisible(false);
        payagentupdatebalancepane.setVisible(false);
        receiveagentupdatebalancepane.setVisible(false);
        payorreceiveagentbalancepane.setVisible(true);
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        ResultSet res = st.executeQuery("select * from demo.agentinformation");

        while(res.next()) {
            if(res.getString("AccountId").equals(agentupdateid.getText())) {
                String temp = res.getString("Balance");
                if(temp == null) val = 0;
                else val = Double.valueOf(temp);
                balance.setText(String.valueOf(val) + " BDT");
                tempemail = res.getString("Email") + "@gmail.com" ;
                break;

            }

        }


    }
    public void updateagentbalancepanerefresh() {
        due.setVisible(false);
        balance.setVisible(false);
        payorreceiveagentbalancepane.setVisible(false);
        payagentupdatebalancepane.setVisible(false);
        receiveagentupdatebalancepane.setVisible(false);
    }

    public void pay() {
        payorreceiveagentbalancepane.setVisible(false);
        receiveagentupdatebalancepane.setVisible(false);
        payagentupdatebalancepane.setVisible(true);

    }
    public void receive() {
        payorreceiveagentbalancepane.setVisible(false);
        payagentupdatebalancepane.setVisible(false);
        receiveagentupdatebalancepane.setVisible(true);
    }
    @FXML
    private TextField payamount, receiveamount;
    @FXML
    private Label payerrormsg,paysuccessmsg, receivesuccessmsg;
    public void agentupdatepayamount() throws Exception {
        int u = Integer.valueOf(payamount.getText());
        if(val < 100 || u > val) {
            payerrormsg.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    payerrormsg.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }

        val-=u;
        String query = String.format("update demo.agentinformation set Balance = %s where AccountId = %s", String.valueOf(val), agentupdateid.getText());
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        paysuccessmsg.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                paysuccessmsg.setVisible(false);
                timer.cancel();
            }
        };
        timer.schedule(task,3000);
        st = new DatabaseFacilites().getConnection().createStatement();
        ResultSet res = st.executeQuery("select * from demo.agentinformation");

        while(res.next()) {
            if(res.getString("AccountId").equals(agentupdateid.getText())) {
                String temp = res.getString("Balance");
                if(temp == null) val = 0;
                else val = Double.valueOf(temp);
                balance.setText(String.valueOf(val) + " BDT");
                tempemail = res.getString("Email") + "@gmail.com" ;
                break;

            }

        }
        String body = String.format("Dear %s, Your Payment of %s BDT is successfully done",agentupdateid.getText(),payamount.getText());
        String sub = "ProKash";

        MailFacilities.sendMail(tempemail,sub,body);
        return;

    }
    public  void agentupdatereceiveamount() throws Exception {
        int u = Integer.valueOf(receiveamount.getText());
        if(val >= 0 || ((-1)*val) < u) {
            receiveerrormsg.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    receiveerrormsg.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        val += u;
        String query = String.format("update demo.agentinformation set Balance = %s where AccountId = %s", String.valueOf(val), agentupdateid.getText());
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        receivesuccessmsg.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                receivesuccessmsg.setVisible(false);
                timer.cancel();
            }
        };
        timer.schedule(task,3000);
        String body = String.format("Dear %s, %s BDT Received successfully",agentupdateid.getText(),receiveamount.getText());
        String sub = "ProKash";
        MailFacilities.sendMail(tempemail,sub,body);
        return;


    }
    @FXML
    private Label receiveerrormsg;
    public void adminlogout() {
        VisibilityUpdate(toppane);
    }
    @FXML
    private TextField sendmoneyamount,sendmoneyaccountid;
    @FXML
    private Label  sendmoneyaccountiderrormsg, sendmoneyamounterrormsg,sendmoneysuccessmsg;
    @FXML
    private Pane sendmoneypane;
    @FXML
    private JFXButton sendbtn;
    @FXML
    private ProgressIndicator sendprogress;
    public void EnteringSendMoneyPane() {
        VisibilityUpdate(sendmoneypane);
    }
    public void sendMoney() throws Exception {
        double cur = 0,cur2 = 0;
        String name = "",email = "",email2 = "";
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("select * from demo.userinformation");
        ResultSet res = st.executeQuery(query);
        int flag = 0;
        while(res.next()) {
            if(res.getString("AccountId").equals(sendmoneyaccountid.getText())) {
                email = res.getString("Email");
                cur = Double.valueOf(res.getString("CurrentBalance"));
                name+=res.getString("FirstName");
                name+=" ";
                name+=res.getString("LastName");
                flag = 1;
            }
            if(res.getString("AccountId").equals(UserAccountId.getText())) {
                email2 = res.getString("Email");
                cur2 = Double.valueOf(res.getString("CurrentBalance"));
            }
        }
        if(flag == 0) {
            sendmoneyaccountiderrormsg.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    sendmoneyaccountiderrormsg.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        boolean val = true;
        double dat = Double.valueOf(sendmoneyamount.getText());



        if(dat<=0 || dat > (cur2 + 5)) {
            sendmoneyamounterrormsg.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    sendmoneyamounterrormsg.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        sendbtn.setVisible(false);
        sendprogress.setVisible(true);
        cur2 -= (dat + 5);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        query = String.format("update demo.userinformation set CurrentBalance = %s where AccountId = %s", String.valueOf(cur2), UserAccountId.getText());
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        new DatabaseFacilites().transactionInsert(UserAccountId.getText(),"Send Money" , sendmoneyamount.getText(),String.valueOf(5),sendmoneyaccountid.getText(),name,dtf.format(now));
        query = String.format("update demo.userinformation set CurrentBalance = %s where AccountId = %s", String.valueOf(dat+cur), sendmoneyaccountid.getText());
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        new DatabaseFacilites().transactionInsert(sendmoneyaccountid.getText(),"Received" , sendmoneyamount.getText(),String.valueOf(0),UserAccountId.getText(),loginusernamelabel.getText(),dtf.format(now));

//        String body = String.format("Dear %s, %s BDT has been sent to %s",loginusernamelabel.getText(),sendmoneyamount.getText(),sendmoneyaccountid.getText());
//        String body2 = String.format("Dear %s, %s BDT has been received from %s",name,sendmoneyamount.getText(),loginusernamelabel.getText());
//        MailFacilities.sendMail(email,"ProKash",body);
//        MailFacilities.sendMail(email2,"ProKash",body2);
        currentbalancelabel.setText(String.valueOf(cur2) + " BDT");
        sendmoneysuccessmsg.setVisible(true);
        sendprogress.setVisible(false);
        sendbtn.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                sendmoneysuccessmsg.setVisible(false);
                sendmoneyaccountid.setText("");
                sendmoneyamount.setText("");
                timer.cancel();
            }
        };
        timer.schedule(task,3000);

    }
    @FXML
    private TextField sendmoneyamount1,sendmoneyaccountid1;
    @FXML
    private Label  sendmoneyaccountiderrormsg1, sendmoneyamounterrormsg1,sendmoneysuccessmsg1;
    @FXML
    private Pane sendmoneypane1;
    @FXML
    private JFXButton sendbtn1;
    public void EnteringPaymentPane() {
        VisibilityUpdate(sendmoneypane1);
    }
    public void payment() throws Exception {
        double cur = 0,cur2 = 0;
        String name = "",email = "",email2 = "";
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("select * from demo.userinformation");
        ResultSet res = st.executeQuery(query);
        int flag = 0;
        while(res.next()) {
            if(res.getString("AccountId").equals(sendmoneyaccountid1.getText())) {
                email = res.getString("Email");
                cur = Double.valueOf(res.getString("CurrentBalance"));
                name+=res.getString("FirstName");
                name+=" ";
                name+=res.getString("LastName");
                flag = 1;
            }
            if(res.getString("AccountId").equals(UserAccountId.getText())) {
                email2 = res.getString("Email");
                cur2 = Double.valueOf(res.getString("CurrentBalance"));
            }
        }
        if(flag == 0) {
            sendmoneyaccountiderrormsg1.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    sendmoneyaccountiderrormsg1.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        boolean val = true;
        double dat = Double.valueOf(sendmoneyamount1.getText());



        if(dat<=0 || dat > cur2) {
            sendmoneyamounterrormsg1.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    sendmoneyamounterrormsg1.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        sendbtn1.setVisible(false);
        cur2 -= (dat);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        query = String.format("update demo.userinformation set CurrentBalance = %s where AccountId = %s", String.valueOf(cur2), UserAccountId.getText());
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        new DatabaseFacilites().transactionInsert(UserAccountId.getText(),"Send Money" , sendmoneyamount1.getText(),String.valueOf(0),sendmoneyaccountid1.getText(),name,dtf.format(now));
        query = String.format("update demo.userinformation set CurrentBalance = %s where AccountId = %s", String.valueOf(dat+cur), sendmoneyaccountid1.getText());
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        new DatabaseFacilites().transactionInsert(sendmoneyaccountid1.getText(),"Received" , sendmoneyamount1.getText(),String.valueOf(0),UserAccountId.getText(),loginusernamelabel.getText(),dtf.format(now));

//        String body = String.format("Dear %s, %s BDT has been sent to %s",loginusernamelabel.getText(),sendmoneyamount.getText(),sendmoneyaccountid.getText());
//        String body2 = String.format("Dear %s, %s BDT has been received from %s",name,sendmoneyamount.getText(),loginusernamelabel.getText());
//        MailFacilities.sendMail(email,"ProKash",body);
//        MailFacilities.sendMail(email2,"ProKash",body2);
        currentbalancelabel.setText(String.valueOf(cur2) + " BDT");
        sendmoneysuccessmsg1.setVisible(true);
        sendbtn1.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                sendmoneysuccessmsg1.setVisible(false);
                sendmoneyaccountid1.setText("");
                sendmoneyamount1.setText("");
                timer.cancel();
            }
        };
        timer.schedule(task,3000);

    }
    @FXML
    private TextField rechargephonenumber,rechargeamount;
    @FXML
    private Label invaliderrmsg,operatorinvaliderrmsg,invaliderrmsg1,rechargesuccessmsg;
    public void mobileRecharge() throws SQLException {
        if(!CheckValidPhoneNumber(rechargephonenumber.getText())) {
            invaliderrmsg.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    invaliderrmsg.setVisible(false);
                    rechargephonenumber.setText("");
                    rechargeamount.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;

        }
        char c = rechargephonenumber.getText().charAt(1);

        int val = Integer.valueOf(c-'0');
        val += 700000;
        int flag = 0;
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        Statement st2 = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("select * from demo.userinformation where AccountId = '%s'",String.valueOf(val));
        ResultSet res = st.executeQuery(query);
        String query2 = String.format("select * from demo.userinformation where AccountId = '%s'",UserAccountId.getText());
        ResultSet res2 = st2.executeQuery(query2);
        if(!res.next()) {
            operatorinvaliderrmsg.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    operatorinvaliderrmsg.setVisible(false);
                    rechargephonenumber.setText("");
                    rechargeamount.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        if(rechargeamount.getText().equals("")) {
            invaliderrmsg1.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    invaliderrmsg1.setVisible(false);
                    rechargephonenumber.setText("");
                    rechargeamount.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        res2.next();
        double s;
        if(res2.getString("CurrentBalance") == null) s = 0;
        else s = Double.valueOf(res2.getString("CurrentBalance"));
        double t;
        if(res.getString("CurrentBalance") == null) t = 0;
        else t = Double.valueOf(res.getString("CurrentBalance"));
        double v = Double.valueOf(rechargeamount.getText());
        double u = s-Double.valueOf(rechargeamount.getText());

        if(u < 0 || Double.valueOf(rechargeamount.getText()) < 0) {
            invaliderrmsg1.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {

                    invaliderrmsg1.setVisible(false);
                    rechargephonenumber.setText("");
                    rechargeamount.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;

        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        query = String.format("update demo.userinformation set CurrentBalance = '%s' where AccountId = '%s'", String.valueOf(t+v), String.valueOf(val));
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        new DatabaseFacilites().transactionInsert(UserAccountId.getText(),"Mobile Recharge" ,rechargeamount.getText(),String.valueOf(0),rechargephonenumber.getText(),res.getString("FirstName"),dtf.format(now));
        query = String.format("update demo.userinformation set CurrentBalance = '%s' where AccountId = '%s'", String.valueOf(u), loginusernamelabel.getText());
        st = new DatabaseFacilites().getConnection().createStatement();
        st.executeUpdate(query);
        new DatabaseFacilites().transactionInsert(String.valueOf(val),"Recharge Received" ,receiveamount.getText(),String.valueOf(0),UserAccountId.getText(),loginusernamelabel.getText(),dtf.format(now));
        currentbalancelabel.setText(String.valueOf(u) + " BDT");
        rechargesuccessmsg.setVisible(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                rechargesuccessmsg.setVisible(false);
                rechargephonenumber.setText("");
                rechargeamount.setText("");
                timer.cancel();
            }
        };
        timer.schedule(task,3000);


    }
    @FXML
    private Pane rechargepane;
    public void EnteringRechargePane() {
        VisibilityUpdate(rechargepane);
    }
    public static  void main(String[] args) throws SQLException {
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("create table demo.r700009 ( TransactionType varChar(255), AccountId varChar(255), UserName varChar(255), Amount varChar(255), Charge varChar(255), Date varChar(255))");
        st.executeUpdate(query);
    }

    @FXML
    private  Pane changepasswordpane;
    @FXML
    private TextField agentchangepass;
    @FXML
    private Label passworderrmsg;
    public void EnteringAgentChangePasswordPane() {
        changepasswordpane.setVisible(true);
    }
    public void agentChangePassword() throws SQLException {
        if(!Validation.CheckPin(agentchangepass.getText())) {
            passworderrmsg.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    passworderrmsg.setVisible(false);
                    agentchangepass.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;
        }
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("update demo.agentinformation set Password = '%s' where AccountId = '%s'",agentchangepass.getText(),agentid.getText());
        st.executeUpdate(query);
        changepasswordpane.setVisible(false);
    }
    public void backtoagentmode() {
        VisibilityUpdate(agentmodepane);
        agentid.setText("");
        agentpin.setText("");
    }
    public void backtoTop() {
        VisibilityUpdate(toppane);
    }
    @FXML
    private TableView<usertransModel>usertable;
    @FXML
    private TableColumn<usertransModel,String>userindexcol,useraccountidcol,usernamecol,useramountcol,userchargecol,userdatecol,usertranscol;
    public void userTransactionHistory() throws SQLException {
        VisibilityUpdate(usertranshist);
        usertable.getItems().clear();
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("select * from demo.r%s",UserAccountId.getText());
        ResultSet res = st.executeQuery(query);
        int i = 1;
        while(res.next()) {
            usertable.getItems().add(new usertransModel(String.valueOf(i),res.getString("AccountId"),res.getString("UserName"),res.getString("Amount"),res.getString("Charge"),res.getString("TransactionType"),res.getString("Date")));
            i++;
        }
    }
    @FXML
    private Pane usertranshist,changepasswordpane1;
    @FXML
    private TextField userchangepass1;
    @FXML
    private Label passworderrmsg1,passwordsuccessmsg;
    public void EnteringUserChangePass() {
        VisibilityUpdate(changepasswordpane1);
    }
    public void userchangepass() throws SQLException {
        if(!checkPass(userchangepass1.getText())) {
            passworderrmsg1.setVisible(true);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    passworderrmsg1.setVisible(false);
                    userchangepass1.setText("");
                    timer.cancel();
                }
            };
            timer.schedule(task,3000);
            return;

        }
        Statement st = new DatabaseFacilites().getConnection().createStatement();
        String query = String.format("update demo.userinformation set Password = '%s',ConfirmPassword = '%s' where AccountId = '%s'",userchangepass1.getText(),userchangepass1.getText(),UserAccountId.getText());

        st.executeUpdate(query);
        passwordsuccessmsg.setVisible(true);
        Timer timer = new Timer();
        String body = String.format("Dear %s, Your Password for ProKash has been Changed",UserAccountId.getText());
        String sub = "ProKash";
        st = new DatabaseFacilites().getConnection().createStatement();
        query = String.format("select * from demo.userinformation where AccountId = '%s'",UserAccountId.getText());
        ResultSet res = st.executeQuery(query);
        res.next();
        String temp = res.getString("Email");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                passwordsuccessmsg.setVisible(false);
                userchangepass1.setText("");
                try {
                    MailFacilities.sendMail(temp,sub,body);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                timer.cancel();
            }
        };
        timer.schedule(task,3000);
        return;

    }
    boolean checkPass(String s) {
        if(s.length() < 6) return false;
        for(int i = 0; i<s.length(); i++) {
            if(s.charAt(i) == ' ') return false;
        }
        return  true;
    }


}