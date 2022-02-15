package com.example.smultan7_assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HydraGameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HydraGameApplication.class.getResource("HydraGame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 850);
        stage.setTitle("Hydra Game");//game title

        //adds image icon to window
        Image icon = new Image("file:src/main/resources/com/example/smultan7_assignment1/HydraIcon.png");
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}