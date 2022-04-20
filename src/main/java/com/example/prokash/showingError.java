package com.example.prokash;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class showingError {
    public static void changeStyleError(TextField textField) {
        textField.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #E74C3C; -fx-border-radius: 20px; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #E74C3C;");
        //textField.setPromptText(textField.getPromptText()+ " Error");
    }
    public static void changeStyleError(ComboBox<String> combobox) {
        combobox.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #E74C3C; -fx-border-radius: 20px; -fx-text-fill: #ffffff;-fx-prompt-text-fill: #E74C3C;");
    }
    public static void changeStyleError(DatePicker datePicker) {
        datePicker.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #E74C3C; -fx-border-radius: 20px; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #E74C3C;");
        //textField.setPromptText(textField.getPromptText()+ " Error");
    }
    public static void changeStyleCorrect(TextField textField) {
        textField.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #ffffff; -fx-border-radius: 20px; -fx-text-fill: #ffffff; ");
        //textField.setPromptText(textField.getPromptText()+ " Error");
    }
    public static void changeStyleCorrect(ComboBox<String> combobox) {
        combobox.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #ffffff; -fx-border-radius: 20px; -fx-text-fill: #ffffff;");
    }
    public static void changeStyleCorrect(DatePicker datePicker) {
        datePicker.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #1F2D3A; -fx-border-radius: 20px; -fx-text-fill: #ffffff; ");
        //textField.setPromptText(textField.getPromptText()+ " Error");
    }
    static void Refresh(TextField firstname, TextField lastname, TextField mothername, TextField fathername, TextField email, TextField occupation, TextField postoffice, TextField city, TextField district, TextField nationality, TextField phonenumber, TextField postalcode, TextField nid, TextField password, TextField confirmpassword, ComboBox<String> religion, ComboBox<String> maritalstatus, ComboBox<String> gender, DatePicker dateofbirth, ComboBox<String> income, AnchorPane AnchorPaneSignUpForm1) {
       firstname.setText("");
       lastname.setText("");
       mothername.setText("");
       fathername.setText("");
       occupation.setText("");
       postoffice.setText("");
       email.setText("");
       city.setText("");
       district.setText("");
       nationality.setText("");
       phonenumber.setText("");
       postalcode.setText("");
       nid.setText("");
       password.setText("");
       confirmpassword.setText("");
       dateofbirth.setValue(null);
       income.setValue(null);
       religion.setValue(null);
       maritalstatus.setValue(null);
       gender.setValue(null);
    }

}
