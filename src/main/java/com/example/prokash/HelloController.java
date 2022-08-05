package com.example.prokash;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
                verificationcodeemaillabel.setText(email.getText());
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
            AccountId =100000 + (databaseWork.NumberOfUsers()+1);

            databaseWork.createTransactionsTable(String.valueOf(AccountId));
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
        if(!databaseWork.UserExist("AccountId",UserAccountId.getText())) {
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
        VisibilityUpdate(homepane);


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
        if(!Validation.CheckValidPhoneNumber(forgetphonenumberverify.getText())) {
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
        UserAccountId.setText("");
        UserPassword.setText("");
    }


    public void EnteringDepositPane() {
        EnteringDepositPaneRefresh();
        VisibilityUpdate(bankingdepositpane);
    }
    public void EnteringDepositPaneRefresh() {
        depositamounttextfield.setText("");
        depositamountlabel.setTextFill(Paint.valueOf("#1F2D3A"));
        depositamountline.setStroke(Paint.valueOf("#1F2D3A"));
        depositaccountidtextfield.setText("");
        depositaccountidlabel.setTextFill(Paint.valueOf("#1F2D3A"));
        depositaccountidline.setStroke(Paint.valueOf("#1F2D3A"));
    }
    public void BackFromEnteringDepositPane() {
        VisibilityUpdate(agentmodebankingpane);
    }
    public void Deposit() throws SQLException {
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        if(!databaseWork.UserExist("AccountId",depositaccountidtextfield.getText())) {
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
            depositaccountidline.setStroke(Paint.valueOf("#1F2D3A"));
            depositaccountidlabel.setTextFill(Paint.valueOf("#1F2D3A"));
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
            depositamountline.setStroke(Paint.valueOf("#1F2D3A"));
            depositamountlabel.setTextFill(Paint.valueOf("#1F2D3A"));
        }

        String amount = databaseWork.GetInformation(depositaccountidtextfield.getText(),"CurrentBalance");
        if(amount == null) amount = "0";
        double value = Double.valueOf(amount) + Double.valueOf(depositamounttextfield.getText());
        databaseWork.Update("CurrentBalance",String.valueOf(value),depositaccountidtextfield.getText());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        databaseWork.transactionInsert(depositaccountidtextfield.getText(), "Deposit", depositamounttextfield.getText(),"0","010101", "Agent",dtf.format(now));
        EnteringDepositPaneRefresh();
        currentbalancelabel.setText(databaseWork.GetInformation(UserAccountId.getText(),"CurrentBalance") + " BDT");
        depositsuccessmessage.setVisible(true);
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
    public void EnteringAgentModeBankingPane() {
        if(!(agentid.getText().equals("010101") && agentpin.getText().equals("123456"))) {
            agentidline.setStroke(Paint.valueOf("red"));
            agentpinline.setStroke(Paint.valueOf("red"));
            agentidlabel.setTextFill(Paint.valueOf("red"));
            agentpinlabel.setTextFill(Paint.valueOf("red"));
            agenterrormessage.setVisible(true);
            Timer timer = new Timer();
            TimerTask timertask = new TimerTask() {
                @Override
                public void run() {
                    agenterrormessage.setVisible(false);
                    timer.cancel();
                }
            };
            timer.schedule(timertask,3000);
            return;
        }
        VisibilityUpdate(agentmodebankingpane);
    }
    public void AgentModePaneRefresh() {
        agentpin.setText("");
        agentid.setText("");
        agentidline.setStroke(Paint.valueOf("#1F2D3A"));
        agentpinline.setStroke(Paint.valueOf("#1F2D3A"));
        agentidlabel.setTextFill(Paint.valueOf("#1F2D3A"));
        agentpinlabel.setTextFill(Paint.valueOf("#1F2D3A"));
    }
    public void EnteringWithdrawBankingPane() {
        EnteringWithdrawPaneRefresh();
        VisibilityUpdate(bankingwithdrawpane);
    }
    public void EnteringWithdrawPaneRefresh() {
        withdrawamounttextfield.setText("");
        withdrawamountlabel.setTextFill(Paint.valueOf("#1F2D3A"));
        withdrawamountline.setStroke(Paint.valueOf("#1F2D3A"));
        withdrawaccountidtextfield.setText("");
        withdrawaccountidlabel.setTextFill(Paint.valueOf("#1F2D3A"));
        withdrawaccountidline.setStroke(Paint.valueOf("#1F2D3A"));
    }
    public void BackFromEnteringWithdrawPane() {
        VisibilityUpdate(agentmodebankingpane);
    }
    public void Withdraw() throws SQLException {
        DatabaseFacilites databaseWork = new DatabaseFacilites();
        if(!databaseWork.UserExist("AccountId",withdrawaccountidtextfield.getText())) {
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
            withdrawaccountidline.setStroke(Paint.valueOf("#1F2D3A"));
            withdrawaccountidlabel.setTextFill(Paint.valueOf("#1F2D3A"));
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
            withdrawamountline.setStroke(Paint.valueOf("#1F2D3A"));
            withdrawamountlabel.setTextFill(Paint.valueOf("#1F2D3A"));
        }

        String amount = databaseWork.GetInformation(withdrawaccountidtextfield.getText(),"CurrentBalance");
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
        databaseWork.Update("CurrentBalance",String.valueOf(value),withdrawaccountidtextfield.getText());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        databaseWork.transactionInsert(withdrawaccountidtextfield.getText(), "Withdraw", withdrawamounttextfield.getText(),String.valueOf(charge),"010101", "Agent",dtf.format(now));
        currentbalancelabel.setText(databaseWork.GetInformation(UserAccountId.getText(),"CurrentBalance") + " BDT");
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

    public void translateanimation(double duration, Node node, Double width) {
        TranslateTransition translateTransition = new TranslateTransition(javafx.util.Duration.seconds(duration),node);
        translateTransition.setByX(width);
        translateTransition.play();
    }


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
        VisibilityUpdate(chartshowingpane);

        translateanimation(2,chartshowingpane,-682.0);

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
        translateanimation(2,chartshowingpane,682.0);
        VisibilityUpdate(filteringchartpane);
    }

    @FXML
    private ComboBox<String> fromyearchoice, frommonthchoice, fromdaychoice, toyearchoice, tomonthchoice, todaychoice, charttypechoice, subjectonchoice, analysistypechoice;


    public void VisualizationFilter() {
        VisualizationFileterRefresh();
        VisibilityUpdate(filteringchartpane);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateanimation(.1,chartshowingpane,682.0);


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
                        makeProfile(clickedRow.getAccountId(),1);
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
//        translateanimation(.1,profileaddressinformationpane,451.0);
//        translateanimation(.1,profiletransactionactivitypane,-451.0);
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
    int profileanimationflag = 0;
    public void profileanimationback() {
//        profileanimationflag  = 1;
//        Pane temppane;
//        if(profileanimationcounter == 0) temppane = profilepersonalinformationpane;
//        else if(profileanimationcounter == 1 || profileanimationcounter == -2) temppane = profiletransactionactivitypane;
//        else temppane = profileaddressinformationpane;
//        translateanimation(.5,temppane,-451.0);
        profileanimationcounter--;
        controlProfileAnimation();
    }
    public void profileanimationnext() {
//        profileanimationflag = 2;
//        Pane temppane;
//        if(profileanimationcounter == 0) temppane = profilepersonalinformationpane;
//        else if(profileanimationcounter == 1 || profileanimationcounter == -2) temppane = profiletransactionactivitypane;
//        else temppane = profileaddressinformationpane;
//        translateanimation(.5,temppane,451.0);
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
//        if(profileanimationflag == 1) {
//            translateanimation(.5,temppane,-451.0);
//            int temp = profileanimationcounter + 2;
//            temp%=3;
//            if(temp == 0) translateanimation(.5,profilepersonalinformationpane,(2*451.0));
//            else if(temp == 1 || temp == -2) translateanimation(.5,profiletransactionactivitypane,(2*451.0));
//            else translateanimation(.5,profileaddressinformationpane,(2*451.0));
//        }
//        else {
//            translateanimation(.5,temppane,451.0);
//            int temp = profileanimationcounter - 2;
//            temp%=3;
//            if(temp == 0) translateanimation(.5,profilepersonalinformationpane,-(2*451.0));
//            else if(temp == 1 || temp == -2) translateanimation(.5,profiletransactionactivitypane,-(2*451.0));
//            else translateanimation(.5,profileaddressinformationpane,-(2*451.0));
//        }
    }

    public void VisibilityUpdate(Pane pane) {
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
        VisibilityUpdate(databaseshowingpane);
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
        VisibilityUpdate(databasefilteringpane);
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
        VisibilityUpdate(databasefilteringpane);
    }


}