package com.example.prokash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ProKash");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:H:\\Programming\\Java\\Prokash\\src\\main\\resources\\com\\example\\prokash\\Icon.jpg"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}