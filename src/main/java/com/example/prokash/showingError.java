package com.example.prokash;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class showingError {
    public static void changeStyle(TextField textField) {
        textField.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #E74C3C; -fx-border-radius: 20px; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #E74C3C;");
        //textField.setPromptText(textField.getPromptText()+ " Error");
    }
    public static void changeStyle(ComboBox<String> combobox) {
        combobox.setStyle("-fx-background-color: #1F2D3A; -fx-border-color: #E74C3C; -fx-border-radius: 20px; -fx-text-fill: #ffffff;-fx-prompt-text-fill: #E74C3C;");
    }
}
