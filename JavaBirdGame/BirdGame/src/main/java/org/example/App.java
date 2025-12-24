package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class App extends Application {


    public App() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        java.net.URL url = getClass().getResource("/birdmain.fxml");
        System.out.println("FXML URL = " + url);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/birdmain.fxml"));
        Parent root = fxmlLoader.load();

        Scene birdscene = new Scene(root);
        stage.setScene(birdscene);
        stage.show();
    }


    @FXML
    private void OnStartButtonClicked(ActionEvent event) {
        System.out.println("Start clicked!");
    }

}
